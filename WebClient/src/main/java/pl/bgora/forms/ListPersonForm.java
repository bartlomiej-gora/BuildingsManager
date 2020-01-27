/**
 * 
 */
package pl.bgora.forms;

import org.apache.struts.action.ActionForm;
import pl.bgora.forms.decorator.PersonWrapper;

import java.util.List;


public class ListPersonForm extends ActionForm {

	private List<PersonWrapper> persons;


	public List<PersonWrapper> getPersons() {
		return persons;
	}


	public void setPersons(List<PersonWrapper> persons) {
		this.persons = persons;
	}
}
