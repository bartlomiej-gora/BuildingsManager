/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import org.apache.struts.action.ActionForm;

/**
 * Formatka usuwania mieszkania.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemoveFlatForm extends ActionForm {

	private Flat flat;

	/**
	 * Zwraca warto�� pola flat
	 * 
	 * @return Warto�� pola flat
	 */
	public Flat getFlat() {
		return flat;
	}

	/**
	 * Ustawia warto�� pola flat
	 * 
	 * @param flat
	 *            Nowa warto�� pola flat
	 */
	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public String getName() {
		Building b = flat.getBuilding();
		return new StringBuilder(b.getStreetName()).append(" ").append(
				b.getStreetNumber()).append("/").append(flat.getFlatNumber())
				.toString();
	}
}
