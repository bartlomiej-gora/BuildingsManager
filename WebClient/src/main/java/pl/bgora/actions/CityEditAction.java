/**
 * 
 */
package pl.bgora.actions;

import com.github.bgora.beans.entity.City;
import com.github.bgora.beans.session.bussiness.CityBeanService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import pl.bgora.forms.CityEditForm;
import pl.bgora.utils.ActionParams;
import pl.bgora.utils.ActionResult;
import pl.bgora.utils.ActionTools;
import pl.bgora.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Akcja edycji S�ownika miast.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class CityEditAction extends Action {

	private CityBeanService cityService;

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
			throws Exception {// NOSONAR
		CityEditForm editForm = (CityEditForm) form; // NOSONAR
		List<City> cities = cityService.getAllCities();
		if (cities == null) {
			cities = new ArrayList<City>();
		}
		editForm.setCities(cities);
		if (ActionTools.buttonHit(request, ActionParams.ADD_CITY)) {
			// Walidacja, czy podano nazw� miasta.
			if (editForm.getNewNameText() == null
					|| editForm.getNewNameText().length() == 0) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage(
						Constants.MSG_EMPTY_CITY_NAME, true);
				errors.add("addCity", message);
				addErrors(request, errors);
			} else {
				City city = new City();
				city.setName(editForm.getNewNameText());
				cityService.save(city);
				cities = cityService.getAllCities();
				if (cities == null) {
					cities = new ArrayList<City>();
				}
				editForm.setCities(cities);
				editForm.setNewNameText("");
			}
			// Walidacja podczas edycji istniej�cego miasta.
		} else if (ActionTools.buttonHit(request, ActionParams.EDIT_CITY_NAME)) {
			if (editForm.getEditedName() == null
					|| editForm.getEditedName().length() == 0) {
				ActionErrors errors = new ActionErrors();
				ActionMessage msg = new ActionMessage(
						Constants.MSG_EMPTY_CITY_NAME_EDIT, true);
				errors.add("editedName", msg);
				addErrors(request, errors);
			} else {
				if (editForm.getCity() == null
						|| editForm.getCity().length() == 0) {
					ActionErrors errors = new ActionErrors();
					errors.add("city", new ActionMessage(
							Constants.NO_CITY_SELECTED, true));
					addErrors(request, errors);

				} else {
					City selected = cityService.getCityByName(editForm
							.getCity());
					selected.setName(editForm.getEditedName());
					cityService.save(selected);
					// od�wierzanie
					cities = cityService.getAllCities();
					if (cities == null) {
						cities = new ArrayList<City>();
					}
					editForm.setCities(cities);
					editForm.setEditedName("");
				}
			}
		}
		return mapping.findForward(ActionResult.SUCCESS);
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
}
