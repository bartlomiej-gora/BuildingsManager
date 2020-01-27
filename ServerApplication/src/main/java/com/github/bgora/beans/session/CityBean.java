package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.City;
import com.github.bgora.beans.session.local.CityBeanLocal;
import com.github.bgora.beans.session.remote.CityBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Session Bean implementation class CityBean
 */
@Stateless(name = "CityBean")
@Clustered
public class CityBean implements CityBeanRemote, CityBeanLocal {

	@PersistenceContext(name = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public CityBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.CityBeanService#getAllCities()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAllCities() throws RemoteException {
		Query q = entityManager.createNamedQuery("getAllCities");
		return q.getResultList();

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public City findById(Long id) throws RemoteException {
		return entityManager.find(City.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(City entity) throws RemoteException {
		if (entity.getCityId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.CityBeanService#getCityByName(java.lang.String)
	 */
	@Override
	public City getCityByName(String name) throws RemoteException {
		Query q = entityManager.createNamedQuery("getCityByNameCount");
		q.setParameter("name", name);
		Long count = (Long) q.getSingleResult();
		if (count == 1) {
			q = entityManager.createNamedQuery("getCityByName");
			q.setParameter("name", name);
			return (City) q.getSingleResult();
		}
		return null;
	}

	@Override
	public void remove(City entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
