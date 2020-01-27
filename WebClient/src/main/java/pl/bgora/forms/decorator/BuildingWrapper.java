/**
 * 
 */
package pl.bgora.forms.decorator;

import com.github.bgora.beans.entity.Building;

/**
 * Wrapper dla budynku.
 * 
 * Implementuje wzorzec dekoratora.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public class BuildingWrapper {

	private Object[] row;

	public String getStreetName() {
		Building building = (Building) row[0];
		return building.getStreetName() + " " + building.getStreetNumber();
	}

	/**
	 *Zwraca liczb� mieszka� dla danego budynku.
	 * 
	 * @return
	 */
	public Long getFlatCount() {
		return ((Long) row[1]);
	}

	/**
	 * Zwraca warto�� pola row
	 * 
	 * @return Warto�� pola row
	 */
	public Object[] getRow() {
		return row.clone();
	}

	/**
	 * Ustawia warto�� pola row
	 * 
	 * @param row
	 *            Nowa warto�� pola row
	 */
	public void setRow(Object[] row) {
		this.row = row.clone();
	}

	/**
	 * Zwraca id budynku.
	 * 
	 * @return id budynku.
	 */
	public Long getId() {
		return ((Building) row[0]).getBuildingId();
	}

}
