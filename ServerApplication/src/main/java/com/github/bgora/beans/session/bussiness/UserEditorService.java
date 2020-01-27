/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.User;

/**
 * Interfejs biznesowy dla stanowego beana do edycji u�ytkownik�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface UserEditorService {
	/**
	 * Ustawia obiekt User.
	 * 
	 * @param user
	 *            nowa warto�� obiektu user.
	 */
	void setUser(User user);

	/**
	 * Zwraca obiekt klasy User.
	 * 
	 * @return obiekt User.
	 */
	User getUser();

	/**
	 * Zapisuje zmiany.
	 */
	void commit();

	/**
	 * Anuluje zmiany
	 */
	void cancel();

	/**
	 * Ustawia obiekt Person
	 * 
	 * @param person
	 *            nowy obiekt Person.
	 */
	void setPerson(Person person);

	/**
	 * Zwraca obiekt Person
	 * 
	 * @return obiekt person.
	 */
	Person getPerson();

	/**
	 * Ustawia Role dla u�ytkownika.
	 * 
	 * @param role
	 *            rola.
	 */
	void setRole(Role role);

	/**
	 * Zwraca role u�ytkownika.
	 * 
	 * @return rola u�ytkownika.
	 */
	Role getRole();

	void setGroup(BMSGroup group);

	BMSGroup getGroup();
}
