/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.bussiness.PersonBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.ListPersonForm;
import pl.bgora.forms.decorator.PersonWrapper;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ListPersonAction extends Action {

	private PersonBeanService personService;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Person> persons = personService.getPersonList("", "", "");
		List<PersonWrapper> wrappers = new ArrayList<PersonWrapper>();
		for (Person p : persons) {
			PersonWrapper wrapper = new PersonWrapper(p);
			wrappers.add(wrapper);
		}
		ListPersonForm personForm = (ListPersonForm) form; // NOSONAR
		personForm.setPersons(wrappers);
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola personService
	 * 
	 * @return Warto�� pola personService
	 */
	public PersonBeanService getPersonService() {
		return personService;
	}

	/**
	 * Ustawia warto�� pola personService
	 * 
	 * @param personService
	 *            Nowa warto�� pola personService
	 */
	public void setPersonService(PersonBeanService personService) {
		this.personService = personService;
	}
}
