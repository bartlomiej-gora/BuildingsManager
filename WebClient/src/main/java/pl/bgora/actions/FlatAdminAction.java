/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.session.bussiness.BuildingEditorService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.EditBuildingForm;
import pl.bgora.forms.FlatAdminForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Akcja administracji mieszkaniami.
 * 
 * Formatka do tej kacji wysiwetla list� mieszka� dla danego budynku.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class FlatAdminAction extends Action {

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
		if (ActionTools.buttonHit(request, ActionParams.BACK)) {
			return mapping.findForward(ActionResult.BACK);
		} else {
			FlatAdminForm adminForm = (FlatAdminForm) form; // NOSONAR
			BuildingEditorService editor = ((EditBuildingForm) request
					.getSession().getAttribute("editBuildingForm"))
					.getBuildingEditor();
			Building building = editor.getBuilding();
			adminForm.setEditor(editor);
			adminForm.setBuildingName(building.getStreetName() + " "
					+ building.getStreetNumber());

		}
		return mapping.findForward(ActionResult.SUCCESS);
	}
}
