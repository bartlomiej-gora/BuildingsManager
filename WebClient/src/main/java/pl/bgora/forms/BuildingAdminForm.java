/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.BuildingWrapper;

import java.util.List;

/**
 * Formatka zarz�dzania budynkami.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class BuildingAdminForm extends ActionForm {

	/**
	 * <code>serialVersionUID</code> -
	 */
	private static final long serialVersionUID = -7385300919776369010L;
	private List<BuildingWrapper> buildings;

	/**
	 * Zwraca warto�� pola buildings
	 * 
	 * @return Warto�� pola buildings
	 */
	public List<BuildingWrapper> getBuildings() {
		return buildings;
	}

	/**
	 * Ustawia warto�� pola buildings
	 * 
	 * @param buildings
	 *            Nowa warto�� pola buildings
	 */
	public void setBuildings(List<BuildingWrapper> builings) {
		this.buildings = builings;
	}

}
