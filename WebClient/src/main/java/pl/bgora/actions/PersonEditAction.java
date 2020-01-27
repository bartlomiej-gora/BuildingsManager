/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.bussiness.PersonBeanService;
import com.github.bgora.beans.session.bussiness.PersonEditorService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.PersonEditForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Klasa akcji edycji osoby.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class PersonEditAction extends Action {

	private PersonBeanService personService;

	private PersonEditorService personEditorService;

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
			throws Exception { // NOSONAR
		PersonEditForm editForm = (PersonEditForm) form;
		if (ActionTools.paramEquals(request, ActionParams.NEW_PERSON, "true")) {
			Person p = new Person();
			personEditorService.setPerson(p);
			editForm.setPersonEditor(personEditorService);
			editForm.setMode(PersonEditForm.Mode.ADD);
		} else if (ActionTools.paramExists(request, ActionParams.ID)) {
			String id = request.getParameter(ActionParams.ID);
			Long longId = Long.valueOf(id);
			Person person = personService.getPersonById(longId);
			personEditorService.setPerson(person);
			editForm.setMode(PersonEditForm.Mode.EDIT);
			editForm.setPersonEditor(personEditorService);
		} else if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			personEditorService = editForm.getPersonEditor();
			personEditorService.cancel();
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.paramExists(request, ActionParams.APPLY)) {
			personEditorService = editForm.getPersonEditor();
			Person p = personEditorService.getPerson();
			p.setName(editForm.getName());
			p.setSurname(editForm.getSurname());
			personEditorService.setPerson(p);
			personEditorService.commit();
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.FIND_ADDRESS)) {
			// editForm.setPersonEditor(personEditorService);
			return mapping.findForward(ActionResult.FIND_ADDRESS);
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

	/**
	 * Zwraca warto�� pola personEditorService
	 * 
	 * @return Warto�� pola personEditorService
	 */
	public PersonEditorService getPersonEditorService() {
		return personEditorService;
	}

	/**
	 * Ustawia warto�� pola personEditorService
	 * 
	 * @param personEditorService
	 *            Nowa warto�� pola personEditorService
	 */
	public void setPersonEditorService(PersonEditorService personEditorService) {
		this.personEditorService = personEditorService;
	}
}
