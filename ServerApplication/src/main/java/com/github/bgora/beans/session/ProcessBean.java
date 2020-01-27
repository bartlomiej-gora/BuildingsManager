package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.RoleTypes;
import com.github.bgora.beans.session.bussiness.ProcessBeanService;
import com.github.bgora.beans.session.local.ProcessBeanLocal;
import com.github.bgora.beans.session.remote.ProcessBeanRemote;
import com.github.bgora.utils.ProcessDispatcher;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Session Bean implementation class ProcessBean
 */
@Stateless(name = "ProcessBean")
@Clustered
public class ProcessBean implements ProcessBeanRemote, ProcessBeanLocal,
		ProcessBeanService {

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public com.github.bgora.beans.entity.MainProcess findById(Long id)
			throws RemoteException {
		return entityManager.find(com.github.bgora.beans.entity.MainProcess.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(com.github.bgora.beans.entity.MainProcess entity)
			throws RemoteException {

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(com.github.bgora.beans.entity.MainProcess entity)
			throws RemoteException {
		if (entity.getId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessBeanService#listProccesses()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MainProcess> listProccessTemplates() throws RemoteException {
		Query q = entityManager.createNamedQuery("getProcessTemplates");
		return q.getResultList();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessBeanService#listActualProcessesForUser(com.github.bgora.beans.entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MainProcess> listActualProcessesForUser(User user)
			throws RemoteException {
		List<MainProcess> result = new LinkedList<MainProcess>();
		if (user.getRole().getRole().equals(RoleTypes.CLIENT)) {
			Query q = entityManager.createNamedQuery("getProcessesForUser");
			q.setParameter("user", user);
			result.addAll(q.getResultList());
		} else {
			Query q = entityManager
					.createNamedQuery("getProcessesForResponsibleUser");
			q.setParameter("user", user);
			List<MainProcess> proccesses = q.getResultList();
			for (MainProcess proc : proccesses) {
				List<ProcessPathElement> pathes = proc.getActualPathes();
				if (pathes != null && pathes.size() != 0) {
					proc.setProcessed(pathes.get(0).getProcessed());
				}
			}
			result.addAll(proccesses);
		}
		return result;

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessBeanService#acceptProccess(com.github.bgora.beans.entity.MainProcess)
	 */
	@Override
	public void acceptProccess(MainProcess proccess) throws RemoteException {
		ProcessDispatcher dispather = new ProcessDispatcher(this.entityManager);
		dispather.dispatch(proccess, true);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessBeanService#refuseProccess(com.github.bgora.beans.entity.MainProcess)
	 */
	@Override
	public void refuseProccess(MainProcess proccess) throws RemoteException {
		ProcessDispatcher dispather = new ProcessDispatcher(this.entityManager);
		dispather.dispatch(proccess, false);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessBeanService#returnProccess(com.github.bgora.beans.entity.MainProcess)
	 */
	@Override
	public void returnProccess(MainProcess process) throws RemoteException {
		ProcessDispatcher dispather = new ProcessDispatcher(this.entityManager);
		dispather.returnProcess(process);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.ProcessBeanService#getById(java.lang.Long)
	 */
	@Override
	public MainProcess getById(Long id) throws RemoteException {
		Query q = entityManager.createNamedQuery("getProcessById");
		q.setParameter("id", id);
		return (MainProcess) q.getSingleResult();
	}
}
