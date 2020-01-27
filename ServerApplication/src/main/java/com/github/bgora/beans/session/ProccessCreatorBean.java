package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.entity.ProposalData;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.ProcessName;
import com.github.bgora.beans.entity.constants.ProposalStatus;
import com.github.bgora.beans.session.local.ProccessCreatorBeanLocal;
import com.github.bgora.beans.session.remote.ProccessCreatorBeanRemote;
import com.github.bgora.utils.ProcessDispatcher;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Session Bean implementation class ProccessCreatorBean
 */
@Stateful(name = "ProccessCreatorBean")
@Clustered
public class ProccessCreatorBean implements ProccessCreatorBeanRemote,
		ProccessCreatorBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	private MainProcess proccess;

	/**
	 * Default constructor.
	 */
	public ProccessCreatorBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessCreatorService#cancel()
	 */
	@Override
	public void cancel() throws RemoteException {
		this.proccess = null;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessCreatorService#createProcess(com.github.bgora.beans.entity.constants.ProcessName,
	 *      com.github.bgora.beans.entity.User)
	 */
	@Override
	public void createProccess(ProcessName procName, User author)
			throws RemoteException {
		Query q = entityManager.createNamedQuery("getProcessTemplateByName");
		q.setParameter("procName", procName);
		MainProcess processTemplate = (MainProcess) q.getSingleResult();
		proccess = createProcessFromTemplate(processTemplate, author);
	}

	/**
	 * Metoda pomocnicz do tworzenia procesu na podstawie szablonu.
	 * 
	 * @param processTemplate
	 *            szablon procesu
	 * @param author
	 *            U�ytkownik sk��daj�cy wniosek.
	 * @return proces.
	 * @throws CloneNotSupportedException
	 */
	private MainProcess createProcessFromTemplate(MainProcess processTemplate,
			User author) {
		MainProcess actual = new MainProcess();
		actual.setActual(Boolean.TRUE);
		actual.setProcessName(processTemplate.getProcessName());
		ProcessPathElement startElement = createStartElement(processTemplate,
				author);
		startElement.setActualProcess(actual);
		startElement.getContent().setMainProcess(actual);
		List<ProcessPathElement> elements = new LinkedList<ProcessPathElement>();
		elements.add(startElement);
		actual.setActualPathes(elements);
		actual.setDisplayName(processTemplate.getDisplayName());
		actual.setCreateDate(new Date());
		actual.setStatus(startElement.getStatus());
		actual.setPlacement(startElement.getPlacement());
		return actual;
	}

	/**
	 * Metoda pomocnicz generuj�ca krok startowy procesu
	 * 
	 * @param processTemplate
	 *            szablon procesu.
	 * @param author
	 *            autor wniosku
	 * @return krok startowy
	 * @throws CloneNotSupportedException
	 */
	private ProcessPathElement createStartElement(MainProcess processTemplate,
			User author) {
		ProcessPathElement element = processTemplate.getTemplatePathes().get(0);
		ProcessPathElement resultElement = new ProcessPathElement();
		resultElement.setActual(Boolean.TRUE);
		resultElement.setContent((ProposalData) getStartContentForProccess(
				processTemplate).clone());
		resultElement.setProcessed(Boolean.FALSE);
		resultElement.setStatus(ProposalStatus.APPLIED);
		resultElement.getContent().setAuthor(author);
		resultElement.getContent().setCreateDate(new Date());
		resultElement.getContent().setActual(Boolean.TRUE);
		resultElement.setPlacement(element.getPlacement());
		resultElement.setStatus(ProposalStatus.APPLIED);
		resultElement.setOrderNumber(element.getOrderNumber());
		resultElement.setDisplayName(element.getDisplayName());
		resultElement.setResponsible(element.getResponsible());
		resultElement.setRefuseStep(element.getRefuseStep());
		resultElement.setNextStep(element.getNextStep());
		resultElement.setPrevStep(element.getPrevStep());
		// dodajmy podpis.
		Person p = author.getPerson();
		if (p != null && p.getName() != null && p.getSurname() != null) {
			StringBuilder builder = new StringBuilder(p.getName());
			builder.append(" ").append(p.getSurname());
			String content = resultElement.getContent().getContent();
			StringBuilder contentBuilder = new StringBuilder(content);
			contentBuilder.append(builder);
			resultElement.getContent().setContent(contentBuilder.toString());
		}
		return resultElement;
	}

	/**
	 * Pobiera content dla procesu.
	 * 
	 * @param processTemplate
	 * @return
	 */
	private ProposalData getStartContentForProccess(MainProcess processTemplate) {
		Query q = entityManager
				.createNamedQuery("getContentForProcessTemplate");
		q.setParameter("proccess", processTemplate);
		return (ProposalData) q.getSingleResult();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessCreatorService#getProcess()
	 */
	@Override
	public MainProcess getProccess() throws RemoteException {
		return this.proccess;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessCreatorService#startProcess(java.lang.String)
	 */
	@Override
	public void startProccess(String content) throws RemoteException {
		ProcessPathElement start = proccess.getActualPathes().get(0);
		start.getContent().setContent(content);
		entityManager.persist(proccess);
		ProcessDispatcher dispatcher = new ProcessDispatcher(this.entityManager);
		dispatcher.dispatch(proccess, true);
		proccess = null;
	}

}
