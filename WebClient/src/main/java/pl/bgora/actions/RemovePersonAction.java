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
import pl.bgora.forms.RemovePersonForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Akcja usuwania osoby.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemovePersonAction extends Action {

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
		if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.buttonHit(request, ActionParams.APPLY)) {
			RemovePersonForm removeForm = (RemovePersonForm) form;// NOSONAR
			personService.remove(removeForm.getPerson());
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.paramExists(request, ActionParams.ID)) {
			RemovePersonForm removeForm = (RemovePersonForm) form;// NOSONAR
			String stringId = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(stringId);
			Person p = personService.findById(id);
			removeForm.setPerson(p);
		}
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
