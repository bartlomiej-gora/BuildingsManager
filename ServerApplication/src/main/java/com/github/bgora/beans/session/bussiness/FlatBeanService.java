package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;

import java.util.List;

/**
 * Interfejs Biznesowy dla session beana zarz�dzaj�cego mieszkaniami.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface FlatBeanService extends AbstractBeanService<Flat> {

	/**
	 * Metoda kt�ra wyszukuje i zwraca list� mieszka� dla podanego budynku.
	 * 
	 * @param building
	 *            budynek dla kt�rego szukamy mieszka�.
	 * @return lista obiekt�w klasy <code>Flat</code>
	 */
	List<Flat> getFlatsForBuilding(Building building);

	/**
	 * Zwraca list� wszystkich mieszka�. Zwracane obiekty s� po��czone z encj�
	 * Building
	 * 
	 * @return lista mieszka�.
	 */
	List<Flat> listFlats();
}
