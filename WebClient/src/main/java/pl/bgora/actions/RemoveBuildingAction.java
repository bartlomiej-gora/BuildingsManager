/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.bussiness.BuildingBeanService;
import com.github.bgora.beans.session.bussiness.FlatBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.RemoveBuildingForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Akcja usuwania budynku.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class RemoveBuildingAction extends Action {

	private BuildingBeanService buildingService;

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
		if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			RemoveBuildingForm removeForm = (RemoveBuildingForm) form; // NOSONAR
			buildingService.remove(removeForm.getBuilding());
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			return mapping.findForward(ActionResult.BACK);
		}
		if (ActionTools.paramExists(request, ActionParams.ID)) {
			RemoveBuildingForm removeForm = (RemoveBuildingForm) form; // NOSONAR
			String stringId = request.getParameter(ActionParams.ID);
			Long id = Long.valueOf(stringId);
			Building b = buildingService.findById(id);
			List<Flat> flats = flatService.getFlatsForBuilding(b);
			b.setFlats(flats);
			removeForm.setBuilding(b);
		}
		return mapping.findForward(ActionResult.SUCCESS);
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
