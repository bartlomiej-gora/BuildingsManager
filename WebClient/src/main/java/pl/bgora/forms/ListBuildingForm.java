/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.BuildingWrapper;

import java.util.List;


public class ListBuildingForm extends ActionForm {

	private List<BuildingWrapper> buildings;


	public List<BuildingWrapper> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<BuildingWrapper> buildings) {
		this.buildings = buildings;
	}
}
