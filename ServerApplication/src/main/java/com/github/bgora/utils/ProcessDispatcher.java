/**
 * 
 */
package com.github.bgora.utils;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.entity.ProposalData;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.GroupNames;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Klasa rozdzielaj�ca zadania.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ProcessDispatcher {

	private final EntityManager entityManager;

	public ProcessDispatcher(EntityManager manager) {
		this.entityManager = manager;
	}

	/**
	 * Uruchamia krok w procesie.
	 * 
	 * @param proccess
	 *            proces w kt�rym uruchamiamy krok.
	 */
	public void dispatch(MainProcess proccess, boolean decision) {
		List<ProcessPathElement> elements = proccess.getActualPathes();
		ProcessPathElement element = elements.get(elements.size() - 1);
		// decyzja pozytywna
		if (decision) {
			Query q = entityManager.createNamedQuery("getNextStep");
			q.setParameter("procName", proccess.getProcessName());
			q.setParameter("number", element.getOrderNumber());
			q.setParameter("status", element.getStatus());
			ProcessPathElement nextElementTemplate = (ProcessPathElement) q
					.getSingleResult();
			ProcessPathElement nextElement = (ProcessPathElement) nextElementTemplate
					.clone();
			nextElement.setActualProcess(proccess);
			proccess.setPlacement(nextElement.getPlacement());
			proccess.setStatus(nextElement.getStatus());
			nextElement.setContent((ProposalData) element.getContent().clone());
			if (nextElement.getResponsible().getGroupName().equals(
					GroupNames.CLIENT)) {
				nextElement.setActuallyResponsible(element.getContent()
						.getAuthor());
			} else {
				User lessJobsUser = getUserForProcess(nextElementTemplate
						.getResponsible().getId());
				User responsible = selectUser(proccess, nextElement,
						lessJobsUser);
				nextElement.setActuallyResponsible(responsible);
			}
			element.setProcessed(Boolean.TRUE);
			nextElement.setProcessed(Boolean.FALSE);
			nextElement.setCreateDate(new Date());
			entityManager.merge(element);
			entityManager.persist(nextElement);
			entityManager.merge(proccess);

			// negatywna - szukamy refuse_step
		} else {
			Query q = entityManager.createNamedQuery("getRefuseStep");
			q.setParameter("procName", proccess.getProcessName());
			q.setParameter("number", element.getOrderNumber());
			q.setParameter("status", element.getStatus());
			ProcessPathElement refuseTemplate = (ProcessPathElement) q
					.getSingleResult();
			ProcessPathElement refuseElement = (ProcessPathElement) refuseTemplate
					.clone();
			refuseElement.setActualProcess(proccess);
			proccess.setStatus(refuseElement.getStatus());
			proccess.setPlacement(refuseElement.getPlacement());
			refuseElement.setContent((ProposalData) element.getContent()
					.clone());
			if (refuseElement.getResponsible().getGroupName().equals(
					GroupNames.CLIENT)) {
				refuseElement.setActuallyResponsible(element.getContent()
						.getAuthor());
			} else {
				User lessJobsUser = getUserForProcess(refuseElement
						.getResponsible().getId());
				User responsible = selectUser(proccess, refuseElement,
						lessJobsUser);
				refuseElement.setActuallyResponsible(responsible);
			}
			element.setProcessed(Boolean.TRUE);
			refuseElement.setProcessed(Boolean.FALSE);
			refuseElement.setCreateDate(new Date());
			entityManager.merge(element);
			entityManager.persist(refuseElement);
			entityManager.merge(proccess);
		}
	}

	/**
	 * Metoda pomocnicza. Ma wksaza�, kt�rego u�ytkownika wybieramy - tego z
	 * najmniejzli�zb� zada�, czy te� tego, kt�ry obs�ugiwa� ju� ten proces.
	 * 
	 * Chodzi o ot �eby komunikacj� w jednej sprawie za�atwia�y te same
	 * sekretarki itp. itd.
	 * 
	 * @param proccess
	 *            Proces ze sciazkami.
	 * @param nextElement
	 *            nowy elelemtn procesu
	 * @param lessJobsUser
	 *            u�ytkownik z namniejsz� liczb� zada�.
	 * @return
	 */
	private User selectUser(MainProcess proccess,
			ProcessPathElement nextElement, User lessJobsUser) {
		List<ProcessPathElement> pathes = proccess.getActualPathes();
		BMSGroup group = nextElement.getResponsible();
		for (ProcessPathElement element : pathes) {
			if ((element.getResponsible() != null)
					&& (element.getResponsible().getGroupName().equals(group
							.getGroupName()))) {
				return element.getActuallyResponsible();
			}
		}
		return lessJobsUser;
	}

	/**
	 * Zwraca u�ytkownika dla procesu.
	 * 
	 * @param argId
	 *            id grupy z jakiej ma by� user.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private User getUserForProcess(Long argId) {
		Query q = entityManager.createNamedQuery("getUsersWithLessJobs");
		q.setParameter("groupID", argId);
		List<BigInteger[]> ids = q.getResultList();
		if ((ids.size() == 0) || (ids.get(0) == null)) {
			throw new IllegalStateException("Brak u�ytkownik�w dla procesu!");
		}
		Object[] objId = ids.get(0);
		BigInteger idInt = (BigInteger) objId[1];
		Long id = idInt.longValue();
		User user = entityManager.find(User.class, id);
		// gdyby by�o wi�cej rekord�w, np. mamy kilku co maj� zero, to wybierz
		// pierwszego
		return user;
	}

	/**
	 * Zwraca proces do poprzedniego kroku.
	 * 
	 * @param process
	 */
	public void returnProcess(MainProcess process) {
		List<ProcessPathElement> elements = process.getActualPathes();
		ProcessPathElement element = elements.get(0);
		Query q = entityManager.createNamedQuery("getNextStep");
		q.setParameter("procName", process.getProcessName());
		q.setParameter("number", element.getOrderNumber() - 1);
		ProcessPathElement prevElementTemplate = (ProcessPathElement) q
				.getSingleResult();
		ProcessPathElement prevElement = (ProcessPathElement) prevElementTemplate
				.clone();
		prevElement.setActualProcess(process);
		process.setPlacement(prevElement.getPlacement());
		process.setStatus(prevElement.getStatus());
		prevElement.setContent((ProposalData) element.getContent().clone());
		if (prevElement.getResponsible().getGroupName().equals(
				GroupNames.CLIENT)) {
			prevElement
					.setActuallyResponsible(element.getContent().getAuthor());
		} else {
			User responsible = getUserForProcess(prevElement.getResponsible()
					.getId());
			prevElement.setActuallyResponsible(responsible);
		}
		element.setProcessed(Boolean.TRUE);
		prevElement.setProcessed(Boolean.FALSE);
		entityManager.merge(element);
		entityManager.persist(prevElement);
		entityManager.merge(process);
	}
}
