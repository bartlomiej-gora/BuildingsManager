/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.City;
import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * Formatka edycji listy s�ownikowej miast.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class CityEditForm extends ActionForm {

	private static final long serialVersionUID = -8590696020781901508L;

	private List<City> cities;

	private String newNameText;

	private String editedName;

	private String city;

	/**
	 * Zwraca warto�� pola cities
	 * 
	 * @return Warto�� pola cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * Zwraca warto�� pola newNameText
	 * 
	 * @return Warto�� pola newNameText
	 */
	public String getNewNameText() {
		return newNameText;
	}

	/**
	 * Ustawia warto�� pola cities
	 * 
	 * @param cities
	 *            Nowa warto�� pola cities
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	/**
	 * Ustawia warto�� pola newNameText
	 * 
	 * @param newNameText
	 *            Nowa warto�� pola newNameText
	 */
	public void setNewNameText(String newNameText) {
		this.newNameText = newNameText;
	}

	/**
	 * Zwraca warto�� pola editedName
	 * 
	 * @return Warto�� pola editedName
	 */
	public String getEditedName() {
		return editedName;
	}

	/**
	 * Ustawia warto�� pola editedName
	 * 
	 * @param editedName
	 *            Nowa warto�� pola editedName
	 */
	public void setEditedName(String editedName) {
		this.editedName = editedName;
	}

	/**
	 * Zwraca warto�� pola city
	 * 
	 * @return Warto�� pola city
	 */
	public String getCity() {
		return city;

	}

	/**
	 * Ustawia warto�� pola city
	 * 
	 * @param city
	 *            Nowa warto�� pola city
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
