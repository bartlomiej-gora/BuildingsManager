/**
 * 
 */
package pl.bgora.forms.decorator;

import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.User;

/**
 * Klasa dekoratora dla formatek do wy�wietlania listy u�ytkownik�w.
 * 
 * Wzorzec projektowy dekorator.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class UsersWrapper {

	private User user;

	private Person person;

	private Role role;

	/**
	 * Zwraca login u�ytkownika.
	 * 
	 * @return login.
	 */
	public String getLogin() {
		return user.getLogin();
	}

	/**
	 * Zwraca id u�ytkownika.
	 * 
	 * @return id u�ytkownika.
	 */
	public Long getId() {
		return user.getUserId();
	}

	/**
	 * Zwraca imi� u�ytkownika.
	 * 
	 * @return imi�.
	 */
	public String getName() {
		return person.getName();
	}

	/**
	 * Zwraca nazwisko u�ytkownika.
	 * 
	 * @return nazwisko.
	 */
	public String getSurname() {
		return person.getSurname();
	}

	/**
	 * Zwraca nazw� roli.
	 * 
	 * @return nazwa roli.
	 */
	public String getRoleName() {
		return role.getRole().name();
	}

	/**
	 * Zwraca warto�� pola user
	 * 
	 * @return Warto�� pola user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Ustawia warto�� pola user
	 * 
	 * @param user
	 *            Nowa warto�� pola user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Zwraca warto�� pola person
	 * 
	 * @return Warto�� pola person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Ustawia warto�� pola person
	 * 
	 * @param person
	 *            Nowa warto�� pola person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Zwraca warto�� pola role
	 * 
	 * @return Warto�� pola role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Ustawia warto�� pola role
	 * 
	 * @param role
	 *            Nowa warto�� pola role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
