package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.local.UserEditorBeanLocal;
import com.github.bgora.beans.session.remote.UserEditorBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Session Bean implementation class UserEditorBean
 */
@Stateful
@Clustered
public class UserEditorBean implements UserEditorBeanRemote,
		UserEditorBeanLocal {

	@PersistenceContext(unitName = "bms")
	EntityManager entityManager;

	private User user;

	/**
	 * Default constructor.
	 */
	public UserEditorBean() {

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#cancel()
	 */
	@Override
	public void cancel() {
		user = null;

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#commit()
	 */
	@Override
	public void commit() {
		if (user.getUserId() == null
				&& (user.getPerson() == null || user.getPerson().getPersonId() == null)) {
			entityManager.persist(user);
		} else {
			entityManager.merge(user);
		}
		user = null;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#getUser()
	 */
	@Override
	public User getUser() {
		return user;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#setUser(com.github.bgora.beans.entity.User)
	 */
	@Override
	public void setUser(User user) {
		this.user = user;

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#setPerson(com.github.bgora.beans.entity.Person)
	 */
	public void setPerson(Person person) {
		this.user.setPerson(person);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#getPerson()
	 */
	public Person getPerson() {
		return user.getPerson();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#setRole(com.github.bgora.beans.entity.Role)
	 */
	public void setRole(Role role) {
		this.user.setRole(role);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#getRole()
	 */
	public Role getRole() {
		return user.getRole();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#setGroup(com.github.bgora.beans.entity.BMSGroup)
	 */
	public void setGroup(BMSGroup group) {
		List<BMSGroup> userGroups = this.user.getGroups();
		userGroups.clear();
		userGroups.add(group);
		this.user.setGroups(userGroups);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.UserEditorService#getGroup()
	 */
	public BMSGroup getGroup() {
		return this.user.getGroups().get(0);
	}
}
