/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.City;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.bussiness.PersonEditorService;
import org.apache.struts.action.ActionForm;

/**
 * Klasa formatki do edycji osoby.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class PersonEditForm extends ActionForm {

	private static final long serialVersionUID = 5123826470478289312L;

	private Person person;

	private PersonEditorService personEditor;

	public enum Mode {
		EDIT, ADD;
	}

	private Mode mode;

	/**
	 * Zwraca warto�� pola mode
	 * 
	 * @return Warto�� pola mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Ustawia warto�� pola mode
	 * 
	 * @param mode
	 *            Nowa warto�� pola mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
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
	 * Zwraca imi�.
	 * 
	 * @return imi�
	 */
	public String getName() {
		return person.getName();
	}

	/**
	 * Zwraca nazwisko.
	 * 
	 * @return nazwisko.
	 */
	public String getSurname() {
		return person.getSurname();
	}

	/**
	 * Zwraca pesel.
	 * 
	 * @return pesel.
	 */
	public String getPesel() {
		return person.getPesel();
	}

	/**
	 * Ustawia imi�
	 * 
	 * @param name
	 *            nowe imi�
	 */
	public void setName(String name) {
		person.setName(name);
	}

	/**
	 * Ustawia nazwisko.
	 * 
	 * @param surname
	 *            nazwisko.
	 */
	public void setSurname(String surname) {
		person.setSurname(surname);
	}

	/**
	 * Ustawia pesel.
	 * 
	 * @param pesel
	 *            pesel
	 */
	public void setPesel(String pesel) {
		person.setPesel(pesel);
	}

	public String getAddress() {
		StringBuilder builder = new StringBuilder();
		if (person.getFlat() != null) {
			Flat f = person.getFlat();
			if (f.getBuilding() != null) {
				Building b = f.getBuilding();
				builder.append(b.getStreetName());
				builder.append(" ");
				builder.append(b.getStreetNumber()).append("/").append(
						f.getFlatNumber());
				if (b.getCity() != null) {
					City c = b.getCity();
					builder.append("\n").append(c.getName());
				}
			}
		}
		return builder.toString();
	}

	/**
	 * Zwraca warto�� pola personEditor
	 * 
	 * @return Warto�� pola personEditor
	 */
	public PersonEditorService getPersonEditor() {
		return personEditor;
	}

	/**
	 * Ustawia warto�� pola personEditor
	 * 
	 * @param personEditor
	 *            Nowa warto�� pola personEditor
	 */
	public void setPersonEditor(PersonEditorService personEditor) {
		this.personEditor = personEditor;
		this.person = personEditor.getPerson();
	}
}
