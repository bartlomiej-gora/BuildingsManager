/**
 * 
 */
package pl.bgora.forms.decorator;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.entity.Person;

/**
 * Klasa dekoratora dla klasy osoba. ��u�y do wyswietlania listy os�b w systemi.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class PersonWrapper {

	private Person person;

	/**
	 * Konstruktor. Tworzy instancj� obiektu
	 */
	public PersonWrapper() {
		person = new Person();
	}

	/**
	 * Konstruktor. Tworzy instancj� obiektu
	 * 
	 * @param p
	 *            Obiekta klasy Person do zdekorowania.
	 */
	public PersonWrapper(Person p) {
		this.person = p;
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
	 * Zwraca imie lokatora.
	 * 
	 * @return imie lokatora.
	 */
	public String getName() {
		return person.getName();
	}

	/**
	 * Zwraca nazwisko lokatora
	 * 
	 * @return nazwisko lokatora.
	 */
	public String getSurname() {
		return person.getSurname();
	}

	/**
	 * Zwraca <code>true</code> je�li lokator jest g��wnym najemc� lokalu.<br>
	 * W przeciwnym wypadku zwraca <code>false</code>
	 * 
	 * @return zy g��wny najemca
	 */
	public String getMainTenant() {
		if (person.getFlat() != null && person.getFlat().getPerson() != null) {
			return String.valueOf(person.getFlat().getPerson().equals(person));
		}
		return String.valueOf(Boolean.FALSE);

	}

	/**
	 * Zwraca �a�cuch okre�laj�cy miejsce zamieszkania.
	 * 
	 * @return miejsce zamieszkania.
	 */
	public String getStreetName() {
		StringBuilder builder = new StringBuilder();
		if (person.getFlat() != null && person.getFlat().getBuilding() != null) {
			Building building = person.getFlat().getBuilding();
			Flat flat = person.getFlat();
			builder.append(building.getStreetName()).append(" ");
			builder.append(building.getStreetNumber()).append("/");
			builder.append(flat.getFlatNumber());
			return builder.toString();
		}
		return "";
	}

	/**
	 * Zwraca id osoby.
	 * 
	 * @return id osoby.
	 */
	public Long getId() {
		return person.getPersonId();
	}
}
