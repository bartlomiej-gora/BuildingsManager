/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.City;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.entity.Pricing;
import com.github.bgora.beans.session.bussiness.BuildingBeanService;
import com.github.bgora.beans.session.bussiness.BuildingEditorService;
import com.github.bgora.beans.session.bussiness.CityBeanService;
import com.github.bgora.beans.session.bussiness.FlatBeanService;
import com.github.bgora.beans.session.bussiness.PricingBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pl.bgora.forms.EditBuildingForm;
import pl.bgora.forms.EditBuildingForm.Mode;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Akcja dodawania/edycji budynk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public final class EditBuildingAction extends Action {

	private BuildingBeanService buildingService;

	private CityBeanService cityService;

	private BuildingEditorService buildingEditor;

	private FlatBeanService flatService;

	private PricingBeanService pricingService;

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
		EditBuildingForm editForm = (EditBuildingForm) form;// NOSONAR
		List<City> cities = cityService.getAllCities();
		// nigdy nie mo�na wy�wietli� listy == null w displaytagu
		if (cities == null) {
			cities = new ArrayList<City>();
		}
		editForm.setCities(cities);
		/*
		 * Wejscie z zewn�trz - Dodawanie nowego budynku.
		 */
		if (ActionTools.paramEquals(request, ActionParams.NEW_BUILDING, "true")) {
			editForm.setMode(Mode.ADD);
			Building newBuilding = new Building();
			newBuilding.setFlats(new ArrayList<Flat>());
			newBuilding.setPricing(new Pricing());
			buildingEditor.setBuiding(newBuilding);
			editForm.setBuildingEditor(buildingEditor);
			/*
			 * Klikni�cie przycisku "poka� mieszkania"
			 */
		} else if (ActionTools.buttonHit(request, ActionParams.SHOW_FLATS)) {
			// nie trzeba nic przekazywa� do requesta bo ca�a formatka jest w
			// sesji.
			return mapping.findForward(ActionResult.SHOW_FLATS);
			/*
			 * Klikni�cie przycisku "Zastosuj" (Zapisz)
			 */
		} else if (ActionTools.buttonHit(request, ActionParams.SUBMIT)) {
			BuildingEditorService editorService = editForm.getBuildingEditor();
			Building building = editorService.getBuilding();
			City city = cityService.getCityByName(editForm.getCity());
			building.setCity(city);
			editorService.setBuiding(building);
			editorService.commit();
			return mapping.findForward(ActionResult.GO_BUILDING_ADMIN);
			/*
			 * Klikni�cie przycisku "Anuluj"
			 */
		} else if (ActionTools.buttonHit(request, ActionParams.CANCEL)) {
			editForm.getBuildingEditor().cancel();
			return mapping.findForward(ActionResult.GO_BUILDING_ADMIN);
		} else if (ActionTools.paramExists(request, ActionParams.ID)) {
			/*
			 * Wejscie z zewn�trz - EDYCJA
			 */
			editForm.setMode(Mode.EDIT);
			String id = request.getParameter(ActionParams.ID);
			assert id != null : "Przy edycji Id nie moze by� == null!";
			Long buildingID = Long.valueOf(id);
			Building building = buildingService.findById(buildingID);
			List<Flat> flats = flatService.getFlatsForBuilding(building);
			building.setFlats(flats);
			buildingEditor.setBuiding(building);
			editForm.setBuildingEditor(buildingEditor);
		}
		// w ka�dym innym wypadku mo�e by� to powr�t z
		// formatki z list� mieszka�
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
	 * Zwraca warto�� pola cityService
	 * 
	 * @return Warto�� pola cityService
	 */
	public CityBeanService getCityService() {
		return cityService;
	}

	/**
	 * Ustawia warto�� pola cityService
	 * 
	 * @param cityService
	 *            Nowa warto�� pola cityService
	 */
	public void setCityService(CityBeanService cityService) {
		this.cityService = cityService;
	}

	/**
	 * Zwraca warto�� pola buildingEditor
	 * 
	 * @return Warto�� pola buildingEditor
	 */
	public BuildingEditorService getBuildingEditor() {
		return buildingEditor;
	}

	/**
	 * Ustawia warto�� pola buildingEditor
	 * 
	 * @param buildingEditor
	 *            Nowa warto�� pola buildingEditor
	 */
	public void setBuildingEditor(BuildingEditorService buildingEditor) {
		this.buildingEditor = buildingEditor;
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

	/**
	 * Zwraca warto�� pola pricingService
	 * 
	 * @return Warto�� pola pricingService
	 */
	public PricingBeanService getPricingService() {
		return pricingService;
	}

	/**
	 * Ustawia warto�� pola pricingService
	 * 
	 * @param pricingService
	 *            Nowa warto�� pola pricingService
	 */
	public void setPricingService(PricingBeanService pricingService) {
		this.pricingService = pricingService;
	}
}
