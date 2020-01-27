/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.City;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.bussiness.BuildingEditorService;
import org.apache.struts.action.ActionForm;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Formatka u�ywana do edycji/dodawania budynk�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class EditBuildingForm extends ActionForm {

	private static final long serialVersionUID = -870745687139927144L;

	private String city;

	private List<City> cities;

	private BuildingEditorService buildingEditor;

	/**
	 * 
	 * Klasa enumeracji dla tryb�w uruchomienia formatki
	 * 
	 * @author Bart�omiej G�ra (Black007pl@gmail.com)
	 * 
	 */
	public enum Mode {
		ADD, EDIT;
	}

	private Mode mode;

	/**
	 * Zwraca warto�� pola mode
	 * 
	 * @return Warto�� pola mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Ustawia warto�� pola mode
	 * 
	 * @param mode
	 *            Nowa warto�� pola mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * Ustawia warto�� nazwy ulicy
	 * 
	 * @param streetName
	 *            nazwa ulicy
	 * @throws RemoteException
	 *             Wyj�tek obiektu edytora.
	 */
	public void setStreetName(String streetName) throws RemoteException {
		Building b = buildingEditor.getBuilding();
		b.setStreetName(streetName);
		buildingEditor.setBuiding(b);
	}

	/**
	 * Zwraca nazwe ulicy
	 * 
	 * @return nazwa ulicy
	 * @throws RemoteException
	 *             Wyj�tek obiektu edytora.
	 */
	public String getStreetName() throws RemoteException {
		return buildingEditor.getBuilding().getStreetName();
	}

	/**
	 * Ustawia numer ulicy
	 * 
	 * @param streetNumber
	 *            numer ulicy
	 * @throws RemoteException
	 *             Wyj�tek obiektu edytora.
	 */
	public void setStreetNumber(String streetNumber) throws RemoteException {
		Building b = buildingEditor.getBuilding();
		b.setStreetNumber(streetNumber);
		buildingEditor.setBuiding(b);
	}

	/**
	 * Zwraca numer ulicy
	 * 
	 * @return numer ulicy
	 * @throws RemoteException
	 *             Wyj�tek obiektu edytora.
	 */
	public String getStreetNumber() throws RemoteException {
		return buildingEditor.getBuilding().getStreetNumber();
	}

	/**
	 * Zwraca ilo�� mieszka� dla danego budynku.
	 * 
	 * @return liczba mieszka�.
	 * @throws RemoteException
	 *             Wyj�tek obiektu edytora.
	 */
	public Integer getFlatCount() throws RemoteException {
		List<Flat> flats = buildingEditor.getFlats();
		return flats != null ? flats.size() : 0;
	}

	/**
	 * Zwraca warto�� pola cities
	 * 
	 * @return Warto�� pola cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * Ustawia warto�� pola cities
	 * 
	 * @param cities
	 *            Nowa warto�� pola cities
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	/**
	 * Zwraca warto�� pola city
	 * 
	 * @return Warto�� pola city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Ustawia warto�� pola city
	 * 
	 * @param city
	 *            Nowa warto�� pola city
	 */
	public void setCity(String city) {
		this.city = city;
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
}
