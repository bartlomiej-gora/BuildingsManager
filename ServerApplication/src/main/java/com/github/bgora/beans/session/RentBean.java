package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Rent;
import com.github.bgora.beans.session.local.RentBeanLocal;
import com.github.bgora.beans.session.remote.RentBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.RemoteException;

/**
 * Session Bean implementation class RentBean
 */
@Stateless(name = "RentBean")
@Clustered
public class RentBean implements RentBeanRemote, RentBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public RentBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Rent findById(Long id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Rent entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Rent entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
