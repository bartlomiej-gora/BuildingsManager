/**
 * 
 */
package pl.bgora.forms;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import org.apache.struts.action.ActionForm;

/**
 * Formatka edycji mieszkania.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class EditFlatForm extends ActionForm {

	private static final long serialVersionUID = -2011687099101613999L;

	private Flat flat;

	private Building building;

	public enum Mode {
		ADD, EDIT;
	};

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
	 * Zwraca warto�� pola flat
	 * 
	 * @return Warto�� pola flat
	 */
	public Flat getFlat() {
		return flat;
	}

	/**
	 * Ustawia warto�� pola flat
	 * 
	 * @param flat
	 *            Nowa warto�� pola flat
	 */
	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	/**
	 * Zwraca numer mieszkania
	 * 
	 * @return numer mieszkania
	 */
	public String getFlatNumber() {
		return flat.getFlatNumber();
	}

	/**
	 * Zwraca liczb� pokoi
	 * 
	 * @return liczba pokoi
	 */
	public Integer getRooms() {
		return flat.getRooms();
	}

	/**
	 * Zwraca opis
	 * 
	 * @return Opis
	 */
	public String getDescription() {
		return flat.getDescription();
	}

	/**
	 * Zwraca stringa okre�laj�cego, czy jest osobna toaleta.
	 * 
	 * @return Warto�� <code>"true"</code> lub <code>"false"</code>
	 */
	public boolean isSeparateToilet() {
		return flat.isSeparateToilet();
	}

	/**
	 * Zwraca warto�� �a�cuchow� okre�laj�c�, czy jest balkon.
	 * 
	 * @return czy w mieszkaniu jest balcon (<code> "true" </code> lub
	 *         <code>false</code>)
	 */
	public boolean isBalcony() {
		return flat.isBalcony();
	}

	/**
	 * Zwraca wielko�� mieszkania w metrach kwadratowych.
	 * 
	 * @return wielko��.
	 */
	public Double getMetters() {
		return flat.getMetters();
	}

	/**
	 * Ustawia numer mieszkania.
	 * 
	 * @param flatNumber
	 *            numer mieszkania.
	 */
	public void setFlatNumber(String flatNumber) {
		this.flat.setFlatNumber(flatNumber);
	}

	/**
	 * Ustawia liczb� pokoi.
	 * 
	 * @param rooms
	 *            liczba pokoi
	 */
	public void setRooms(Integer rooms) {
		this.flat.setRooms(rooms);
	}

	/**
	 * Ustawia opis mieszkania.
	 * 
	 * @param description
	 *            opis
	 */
	public void setDescription(String description) {
		this.flat.setDescription(description);
	}

	/**
	 * Czy w mieszkaniu jest osobno toaleta i �azienka?
	 * 
	 * Przychodz�ca warto�c pochodzi z kontrolki Struts CheckBox. Tak wi�c
	 * warto�� mo�e by� r�wna "on", lub "off". Je�li warto�� = "on", to
	 * w�a�ciwo�� obiektu jest ustawiana na <code>true</code>. W przeciwnym
	 * wypadku w�a�ciwo�� <code>SeparateToilet</code> przyjmuje warto��
	 * <code>false</code>
	 * 
	 * @param separateToilet
	 *            warto�c stringowa (<code>"on"</code> lub <code>"off</code>")
	 */
	public void setSeparateToilet(boolean separateToilet) {
		this.flat.setSeparateToilet(separateToilet);
	}

	/**
	 * Czy w mieszkaniu jest balkon?
	 * 
	 * Przychodz�ca warto�c pochodzi z kontrolki Struts CheckBox. Tak wi�c
	 * warto�� mo�e by� r�wna "on", lub "off". Je�li warto�� = "on", to
	 * w�a�ciwo�� obiektu jest ustawiana na <code>true</code>. W przeciwnym
	 * wypadku w�a�ciwo�� <code>Balcony</code> przyjmuje warto��
	 * <code>false</code>
	 * 
	 * @param balcony
	 *            warto�c stringowa (<code>"on"</code> lub <code>"off</code>")
	 */
	public void setBalcony(boolean balcony) {
		this.flat.setBalcony(balcony);
	}

	/**
	 * Ustawia powierzchnie mieszkania.
	 * 
	 * @param metters
	 *            powierchnia mieszkania w metrach kwadratowych.
	 */
	public void setMetters(Double metters) {
		this.flat.setMetters(metters);
	}

	/**
	 * Zwraca warto�� pola building
	 * 
	 * @return Warto�� pola building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * Ustawia warto�� pola building
	 * 
	 * @param building
	 *            Nowa warto�� pola building
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	// @Override
	// public void reset(ActionMapping mapping, HttpServletRequest request) {
	// if (flat != null) {
	// flat.setBalcony(false);
	// flat.setSeparateToilet(false);
	// }
	// }

}
