/**
 * 
 */
package pl.bgora.forms.decorator;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;

/**
 * Klasa okalaj�ca obiekt klasy Flat.
 * 
 * S�u�y do wy�wietlania adres�w na li�cie wyboru adresu dla osoby.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class AddressDecorator {

	private Flat flat;

	public AddressDecorator(Flat flat) {
		this.flat = flat;
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
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (flat != null) {
			if (flat.getBuilding() != null) {
				Building b = flat.getBuilding();
				builder.append(b.getStreetName()).append(" ").append(
						b.getStreetNumber()).append("/").append(
						flat.getFlatNumber());
			}
		}
		return builder.toString();
	}

	public String getaddress() {
		return toString();
	}

	public Long getId() {
		return flat.getFlatId();
	}
}
