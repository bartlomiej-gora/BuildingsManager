package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.ProcessPathElement;
import com.github.bgora.beans.session.bussiness.ProcessPathElementBeanService;
import com.github.bgora.beans.session.local.ProcessPathElementBeanLocal;
import com.github.bgora.beans.session.remote.ProcessPathElementBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.RemoteException;

/**
 * Session Bean implementation class ProcessPathElementBean
 */
@Stateless(name = "ProcessPathElementBean")
@Clustered
public class ProcessPathElementBean implements ProcessPathElementBeanRemote,
		ProcessPathElementBeanLocal, ProcessPathElementBeanService {

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public ProcessPathElement findById(Long id) throws RemoteException {
		return entityManager.find(ProcessPathElement.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(ProcessPathElement entity) throws RemoteException {

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(ProcessPathElement entity) throws RemoteException {
		if (entity.getId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}
}
