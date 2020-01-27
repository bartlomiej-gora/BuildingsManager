package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Pricing;
import com.github.bgora.beans.session.local.PricingBeanLocal;
import com.github.bgora.beans.session.remote.PricingBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;

/**
 * Session Bean implementation class PricingBean
 */
@Stateless(name = "PricingBean")
@Clustered
public class PricingBean implements PricingBeanRemote, PricingBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PricingBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Pricing findById(Long id) throws RemoteException {
		return entityManager.find(Pricing.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Pricing entity) throws RemoteException {
		if (entity.getPricingId() == null) {
			entityManager.persist(entity);
		} else if (entity.getPricingId() != null) {
			entityManager.merge(entity);
		}
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.PricingBeanService#getPricingForBuilding(com.github.bgora.beans.entity.Building)
	 */
	@Override
	public Pricing getPricingForBuilding(Building building)
			throws RemoteException {
		Pricing pricing = null;
		Query q = entityManager.createNamedQuery("getPricingForBuildingCount");
		q.setParameter("building", building);
		Long count = (Long) q.getSingleResult();
		if (count == 1) {
			q = entityManager.createNamedQuery("getPricingForBuilding");
			q.setParameter("building", building);
			pricing = (Pricing) q.getSingleResult();
		}
		return pricing;
	}

	@Override
	public void remove(Pricing entity) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
