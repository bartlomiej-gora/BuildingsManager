package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.local.PersonEditorBeanLocal;
import com.github.bgora.beans.session.remote.PersonEditorBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PersonEditorBean
 */
@Stateful(name = "PersonEditorBean")
@Clustered
public class PersonEditorBean implements PersonEditorBeanRemote,
		PersonEditorBeanLocal {

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	private Person person;

	/**
	 * Default constructor.
	 */
	public PersonEditorBean() {

	}

	@Override
	public void cancel() {
		person = null;

	}

	@Override
	public void commit() {
		if (person.getPersonId() != null
				|| (person.getFlat() != null && person.getFlat().getFlatId() != null)) {
			entityManager.merge(person);
		} else {
			entityManager.persist(person);
		}

	}

	@Override
	public Person getPerson() {
		return person;
	}

	@Override
	public void setPerson(Person person) {
		this.person = person;

	}

}
