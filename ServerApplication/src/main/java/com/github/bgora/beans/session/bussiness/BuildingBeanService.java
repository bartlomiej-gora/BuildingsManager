/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.utils.RelationOperator;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfejs biznesowy dla Session beana zarz�dzaj�cego budynkami.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface BuildingBeanService extends AbstractBeanService<Building> {

	/**
	 * Zwraca list� budynk�w o podanej nazwie, lub o podanej ilo�ci
	 * apartament�w.
	 * 
	 * @param streetName
	 *            Nazwa ulicy
	 * @param flatCount
	 *            ilo�� mieszka�.
	 * @param operator
	 *            Operator relacji dla liczby mieszka�
	 * @return Lista zawiraj�ca tablice Object - 0 element - Budynek 1 - element
	 *         - ilo�� mieszka�.
	 * @throws RemoteException
	 */
	List<Object[]> getBuildings(String streetName, Long flatCount,
			RelationOperator operator) throws RemoteException;

	/**
	 * Zwraca budynek dla podanego mieszkania.
	 * 
	 * @param flat
	 *            mieszkaniedla kt�rego szukamy budynku.
	 * @return budynek.
	 * @throws RemoteException
	 */
	Building getBuildingForFlat(Flat flat) throws RemoteException;

}
