/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Building;
import org.apache.struts.action.ActionForm;

/**
 * Formatka Usuwania budynku.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemoveBuildingForm extends ActionForm {

	private Building building;

	/**
	 * Zwraca warto�� pola building
	 * 
	 * @return Warto�� pola building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * Ustawia warto�� pola building
	 * 
	 * @param building
	 *            Nowa warto�� pola building
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * Zwraca nazw� budynku = ulica i numer.
	 * 
	 * @return nazwa budynku.
	 */
	public String getStreetName() {
		return new StringBuilder(building.getStreetName()).append(" ").append(
				building.getStreetNumber()).toString();
	}
}
