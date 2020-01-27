package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.ProposalData;
import com.github.bgora.beans.session.bussiness.ProposalDataBeanService;
import com.github.bgora.beans.session.local.ProposalDataBeanLocal;
import com.github.bgora.beans.session.remote.ProposalDataBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.RemoteException;

/**
 * Session Bean implementation class ProposalDataBean
 */
@Stateless(name = "ProposalDataBean")
@Clustered
public class ProposalDataBean implements ProposalDataBeanRemote,
		ProposalDataBeanLocal, ProposalDataBeanService {

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public ProposalData findById(Long id) throws RemoteException {
		return entityManager.find(ProposalData.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(ProposalData entity) throws RemoteException {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(ProposalData entity) throws RemoteException {
		if (entity.getId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

}
