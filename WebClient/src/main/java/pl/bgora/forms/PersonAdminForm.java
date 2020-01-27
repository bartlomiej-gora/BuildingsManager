/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.PersonWrapper;

import java.util.List;

/**
 * Klasa formatki z administracj� osobami.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class PersonAdminForm extends ActionForm {

	private List<PersonWrapper> persons;

	/**
	 * Zwraca warto�� pola persons
	 * 
	 * @return Warto�� pola persons
	 */
	public List<PersonWrapper> getPersons() {
		return persons;
	}

	/**
	 * Ustawia warto�� pola persons
	 * 
	 * @param persons
	 *            Nowa warto�� pola persons
	 */
	public void setPersons(List<PersonWrapper> persons) {
		this.persons = persons;
	}
}
