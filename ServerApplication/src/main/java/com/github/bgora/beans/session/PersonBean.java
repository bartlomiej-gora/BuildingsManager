package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.local.PersonBeanLocal;
import com.github.bgora.beans.session.remote.PersonBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean sesyjny bezstanowy do obs�ugi ziaren encyjnych klasy Person.
 * 
 * @author Bart�omiej G�ra
 * 
 */

@Stateless(name = "PersonBean")
@Clustered
public class PersonBean implements PersonBeanRemote, PersonBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PersonBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Person findById(Long id) throws RemoteException {
		return entityManager.find(Person.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Person entity) throws RemoteException {
		if (entity.getPersonId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	/**
	 * 
	 Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.PersonBeanService#getPersonList(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersonList(String name, String surname, String street)
			throws RemoteException {
		List<Person> persons = new ArrayList<Person>();
		Query q = entityManager.createNamedQuery("getPersonsWithFlats");
		q.setParameter("name", name + "%");
		q.setParameter("surname", surname + "%");
		persons = q.getResultList();
		for (Person p : persons) {
			q = entityManager.createNamedQuery("getBuildingCountForFlat");
			q.setParameter("flat", p.getFlat());
			Long count = (Long) q.getSingleResult();
			if (count == 1) {
				q = entityManager.createNamedQuery("getBuildingForFlat");
				q.setParameter("flat", p.getFlat());
				Building b = (Building) q.getSingleResult();
				p.getFlat().setBuilding(b);
			}
		}
		// filtrowanie po nazwach ulic.
		if (street != null && street.length() > 0) {
			for (Person p : persons) {
				if (p.getFlat().getBuilding() == null
						|| !p.getFlat().getBuilding().getStreetName()
								.startsWith(street)) {
					persons.remove(p);
				}
			}
		}

		return persons;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @throws RemoteException
	 * 
	 * @see com.github.bgora.beans.session.bussiness.PersonBeanService#getPersonById(java.lang.Long)
	 */
	@Override
	public Person getPersonById(Long id) throws RemoteException {
		Query q = entityManager.createNamedQuery("getPersonById");
		q.setParameter("id", id);
		return (Person) q.getSingleResult();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(Person entity) throws RemoteException {
		entity.setRemoved(Boolean.TRUE);
		entityManager.merge(entity);

	}
}
