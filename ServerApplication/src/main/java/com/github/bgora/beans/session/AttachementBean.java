package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Attachement;
import com.github.bgora.beans.session.local.AttachementBeanLocal;
import com.github.bgora.beans.session.remote.AttachementBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.RemoteException;

/**
 * Session Bean implementation class AttachementBean
 */
@Stateless(name = "AttachementBean")
@Clustered
public class AttachementBean implements AttachementBeanRemote,
		AttachementBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AttachementBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Attachement findById(Long id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Attachement entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Attachement entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
