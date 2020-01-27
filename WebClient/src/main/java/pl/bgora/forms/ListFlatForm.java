/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.AddressDecorator;

import java.util.List;


public class ListFlatForm extends ActionForm {

	private List<AddressDecorator> flats;


	public List<AddressDecorator> getFlats() {
		return flats;
	}


	public void setFlats(List<AddressDecorator> flats) {
		this.flats = flats;
	}
}
