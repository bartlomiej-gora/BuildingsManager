/**
 * 
 */
package pl.bgora.forms.decorator;

import com.github.bgora.beans.entity.Flat;

/**
 * Klasa obudowywuj�ca obiekty mieszkania. Realizuje wzorzec projektowy
 * Dekoratora.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class FlatWrapper {

	private final Flat flat;

	/**
	 * 
	 * Konstruktor. Tworzy instancj� obiektu
	 */
	public FlatWrapper() {
		this.flat = new Flat();
	}

	/**
	 * 
	 * Konstruktor. Tworzy instancj� obiektu.
	 * 
	 * @param flat
	 *            obiekt mieszkania do dekorowania.
	 */
	public FlatWrapper(Flat flat) {
		this.flat = flat;
	}

	/**
	 * Ustawia numer mieszkania.
	 * 
	 * @param number
	 *            numer mieszkania.
	 */
	public void setFlatNumber(String number) {
		flat.setFlatNumber(number);
	}

	/**
	 * Zwraca numer mieszkania.
	 * 
	 * @return numer mieszkania.
	 */
	public String getFlatNumber() {
		return flat.getFlatNumber();
	}

	/**
	 * Ustawia liczb� pokoi.
	 * 
	 * @param rooms
	 *            liczba pokoi.
	 */
	public void setRooms(String rooms) {
		flat.setRooms(Integer.valueOf(rooms));
	}

	/**
	 * Zwraca liczb� pokoi.
	 * 
	 * @return liczba pokoi.
	 */
	public String getRooms() {
		return String.valueOf(flat.getRooms());
	}

	/**
	 * Ustawia metra� mieszkania w metrach kwadratowych.
	 * 
	 * 
	 * @param metters
	 *            metra� mieszkania.
	 */
	public void setMetters(String metters) {
		flat.setMetters(Double.valueOf(metters.replace(",", ".").replace(" ",
				"")));
	}

	/**
	 * Zwraca metra� mieszkania.
	 * 
	 * @return metra�
	 */
	public String getMetters() {
		return String.valueOf(flat.getMetters());
	}

	/**
	 * Ustawia w�a�ciwo�� okre�laj�c�, czy jest balkon w mieszkaniu.
	 * 
	 * @param balcony
	 *            <code>"true"</code> - jest balkon <br>
	 *            <code>"false"</code> - brak balkonu
	 */
	public void setBalcony(String balcony) {
		flat.setBalcony(Boolean.valueOf(balcony));
	}

	/**
	 * Czy jest balkon w mieskzaniu?
	 * 
	 * @return <code>"true"</code> - jest balkon <br>
	 *         <code>"false"</code> - brak balkonu
	 */
	public String getBalcony() {
		return String.valueOf(flat.isBalcony());
	}

	/**
	 * Ustawia w�a�ciwo�� okre�laj�ca, czy toaleta jest osobno, czy razem z
	 * �azienk�.
	 * 
	 * @param toilet
	 *            <code>"true"/"false"</code>
	 */
	public void setSeparateToilet(String toilet) {
		flat.setSeparateToilet(Boolean.valueOf(toilet));
	}

	/**
	 * Czy jest osobna toaleta?
	 * 
	 * @return <code>"true"/"false"</code>
	 */
	public String getSeparateToilet() {
		return String.valueOf(flat.isSeparateToilet());
	}

	/**
	 * Ustawia opis mieszkania.
	 * 
	 * @param desc
	 *            opis.
	 */
	public void setDescription(String desc) {
		flat.setDescription(desc);
	}

	/**
	 * Zwraca opis mieszkania.
	 * 
	 * @return opis mieszkania.
	 */
	public String getDescription() {
		return flat.getDescription();
	}

	/**
	 * Zwraca id mieszkania.
	 * 
	 * @return id mieszkania.
	 */
	public Long getId() {
		return flat.getFlatId();
	}
}
