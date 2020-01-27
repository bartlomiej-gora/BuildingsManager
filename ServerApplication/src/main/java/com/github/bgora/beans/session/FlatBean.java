package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.local.FlatBeanLocal;
import com.github.bgora.beans.session.remote.FlatBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Session Bean implementation class FlatBean
 */
@Stateless(name = "FlatBean")
@Clustered
public class FlatBean implements FlatBeanRemote, FlatBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Konstruktor. Tworzy instancj� obiektu
	 */
	public FlatBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Flat findById(Long id) throws RemoteException {
		return entityManager.find(Flat.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Flat entity) throws RemoteException {
		entityManager.persist(entity);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.FlatBeanService#getFlatsForBuilding(com.github.bgora.beans.entity.Building)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Flat> getFlatsForBuilding(Building building) {
		Query q = entityManager.createNamedQuery("getFlatsForBuilding");
		q.setParameter("building", building);
		return q.getResultList();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.FlatBeanService#listFlats()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Flat> listFlats() {
		Query q = entityManager.createNamedQuery("getAllFlatsWithBuilding");
		return q.getResultList();
	}

	@Override
	public void remove(Flat entity) throws RemoteException {
		entity = entityManager.merge(entity);
		Building b = entity.getBuilding();
		List<Flat> flats = b.getFlats();
		flats.remove(entity);
		b.setFlats(flats);
		entity.setBuilding(null);
		List<Person> persons = entity.getPersons();
		for (Person p : persons) {
			p.setFlat(null);
			entityManager.merge(p);
		}
		entityManager.merge(b);
		entityManager.remove(entity);
		entityManager.flush();

	}
}
