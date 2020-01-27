/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Person;
import org.apache.struts.action.ActionForm;

/**
 * Formatka usuwania osoby
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemovePersonForm extends ActionForm {

	private Person person;

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
	 * Zwraca imi� i nazwisko usuwanej osoby.
	 * 
	 * @return imi� i nazwisko.
	 */
	public String getName() {
		return new StringBuilder(person.getName()).append(" ").append(
				person.getSurname()).toString();
	}
}
