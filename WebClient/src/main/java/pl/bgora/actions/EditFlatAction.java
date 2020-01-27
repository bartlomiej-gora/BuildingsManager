/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.bussiness.BuildingEditorService;
import com.github.bgora.beans.session.bussiness.FlatBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.EditFlatForm;
import pl.bgora.forms.EditFlatForm.Mode;
import pl.bgora.forms.FlatAdminForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Akcja edycji mieszkania.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class EditFlatAction extends Action {

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
			throws Exception { // NOSONAR
		if (ActionTools.buttonHit(request, ActionParams.APPLY)) {
			FlatAdminForm adminForm = (FlatAdminForm) request.getSession()
					.getAttribute("flatsAdminForm"); // NOSONAR
			List<Flat> flats = adminForm.getEditor().getFlats();
			EditFlatForm editForm = (EditFlatForm) form; // NOSONAR
			Flat flat = editForm.getFlat();
			if (editForm.getMode().equals(EditFlatForm.Mode.ADD)) {
				flats.add(flat);
			} else if (editForm.getMode().equals(EditFlatForm.Mode.EDIT)) {
				flats.remove(flat);
				flats.add(flat);
			}
			adminForm.getEditor().setFlats(flats);
			return mapping.findForward(ActionResult.BACK);

		} else if (ActionTools.buttonHit(request, ActionParams.BACK)) {
			return mapping.findForward(ActionResult.BACK);
		} else if (ActionTools.paramEquals(request, ActionParams.NEW_FLAT,
				"true")) {
			Flat flat = new Flat();
			EditFlatForm editForm = (EditFlatForm) form;
			editForm.setFlat(flat);
			Building building = ((FlatAdminForm) request.getSession()
					.getAttribute("flatsAdminForm")).getEditor().getBuilding();
			editForm.setBuilding(building);
			editForm.setMode(Mode.ADD);
			return mapping.findForward(ActionResult.SUCCESS);
			// edycja
		} else if (ActionTools.paramExists(request, ActionParams.ID)) {
			String idString = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(idString);
			FlatAdminForm adminForm = (FlatAdminForm) request.getSession()
					.getAttribute("flatsAdminForm"); // NOSONAR
			BuildingEditorService editor = adminForm.getEditor();
			Flat flat = new Flat();
			flat.setFlatId(id);
			int index = editor.getFlats().indexOf(flat);
			flat = editor.getFlats().get(index);
			assert flat != null : "Mieszkanie nie mo�e by� null podczas edycji!";
			EditFlatForm editForm = (EditFlatForm) form;
			editForm.setFlat(flat);
			editForm.setMode(Mode.EDIT);
			return mapping.findForward(ActionResult.SUCCESS);
		}
		// Je�li kto� klikn�� edycj�, na dopiero co dodanym mieszkaniu,
		// to nic nie r wr�c do listy mieszka�.
		return mapping.findForward(ActionResult.BACK);
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
