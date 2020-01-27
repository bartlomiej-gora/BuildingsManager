/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.entity.Person;
import com.github.bgora.beans.session.bussiness.FlatBeanService;
import com.github.bgora.beans.session.bussiness.PersonEditorService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.ListFlatForm;
import pl.bgora.forms.PersonEditForm;
import pl.bgora.forms.decorator.AddressDecorator;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;
import pl.bgora.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Akcja wylistowania mieszka� dla obiekt�w osoby.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ListFlatAction extends Action {

	private FlatBeanService flatService;

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
		PersonEditForm editForm = (PersonEditForm) request.getSession()
				.getAttribute(Constants.PERSON_EDIT_FORM);
		PersonEditorService personService = editForm.getPersonEditor();
		ListFlatForm listForm = (ListFlatForm) form; // NOSONAR
		if (ActionTools.paramExists(request, ActionParams.FLAT_ID)) {
			String stringId = request.getParameter(ActionParams.FLAT_ID);
			Long id = Long.valueOf(stringId);
			Flat flat = flatService.findById(id);
			Person p = editForm.getPerson();
			p.setFlat(flat);
			personService.setPerson(p);
			editForm.setPersonEditor(personService);
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.buttonHit(request, ActionParams.BACK)) {
			return mapping.findForward(ActionResult.BACK);
		}
		List<Flat> flats = flatService.listFlats();
		List<AddressDecorator> decorators = new LinkedList<AddressDecorator>();
		for (Flat f : flats) {
			AddressDecorator decorator = new AddressDecorator(f);
			decorators.add(decorator);
		}
		listForm.setFlats(decorators);
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola flatService
	 * 
	 * @return Warto�� pola flatService
	 */
	public FlatBeanService getFlatService() {
		return flatService;
	}

	/**
	 * Ustawia warto�� pola flatService
	 * 
	 * @param flatService
	 *            Nowa warto�� pola flatService
	 */
	public void setFlatService(FlatBeanService flatService) {
		this.flatService = flatService;
	}
}
