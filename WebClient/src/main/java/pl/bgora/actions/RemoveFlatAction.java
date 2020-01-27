/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.bussiness.BuildingBeanService;
import com.github.bgora.beans.session.bussiness.BuildingEditorService;
import com.github.bgora.beans.session.bussiness.FlatBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.EditBuildingForm;
import pl.bgora.forms.RemoveFlatForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Akcja uzusawnia mieszkania.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemoveFlatAction extends Action {

	private FlatBeanService flatService;

	private BuildingBeanService buildingService;

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
		if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			RemoveFlatForm removeForm = (RemoveFlatForm) form; // NOSONAR
			EditBuildingForm editForm = (EditBuildingForm) request.getSession()
					.getAttribute("editBuildingForm");
			BuildingEditorService editor = editForm.getBuildingEditor();
			editor.removeFlat(removeForm.getFlat());
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.paramExists(request, ActionParams.ID)) {
			RemoveFlatForm removeForm = (RemoveFlatForm) form; // NOSONAR
			String stringId = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(stringId);
			Flat f = flatService.findById(id);
			Building b = buildingService.getBuildingForFlat(f);
			f.setBuilding(b);
			removeForm.setFlat(f);
		}
		return mapping.findForward(ActionResult.SUCCESS);
	}

	/**
	 * Zwraca warto�� pola flatServie
	 * 
	 * @return Warto�� pola flatServie
	 */
	public FlatBeanService getFlatService() {
		return flatService;
	}

	/**
	 * Ustawia warto�� pola flatServie
	 * 
	 * @param flatServie
	 *            Nowa warto�� pola flatServie
	 */
	public void setFlatService(FlatBeanService flatService) {
		this.flatService = flatService;
	}

	/**
	 * Zwraca warto�� pola buildingService
	 * 
	 * @return Warto�� pola buildingService
	 */
	public BuildingBeanService getBuildingService() {
		return buildingService;
	}

	/**
	 * Ustawia warto�� pola buildingService
	 * 
	 * @param buildingService
	 *            Nowa warto�� pola buildingService
	 */
	public void setBuildingService(BuildingBeanService buildingService) {
		this.buildingService = buildingService;
	}
}
