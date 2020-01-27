/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.session.bussiness.BuildingBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.ListBuildingForm;
import pl.bgora.forms.decorator.BuildingWrapper;
import pl.bgora.utils.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Akcja wy�wietlania listy budynk�w do wyboru dla odstarczenie adresu dla
 * obiektu person.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class ListBuildingAction extends Action {

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
		ListBuildingForm listForm = (ListBuildingForm) form; // NOSONAR
		List<Object[]> buildings = buildingService.getBuildings("", null, null);
		List<BuildingWrapper> wrappers = new ArrayList<BuildingWrapper>();
		if (buildings != null) {
			for (Object[] obj : buildings) {
				BuildingWrapper wrapper = new BuildingWrapper();
				wrapper.setRow(obj);
				wrappers.add(wrapper);
			}
		}
		listForm.setBuildings(wrappers);
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
}
