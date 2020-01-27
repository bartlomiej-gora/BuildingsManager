/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.City;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfejs biznesowy dla session beana obs�uguj�cego miasta
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface CityBeanService extends AbstractBeanService<City> {

	/**
	 * Metoda zwraca list� wszystkich miast.
	 * 
	 * @return Lista wszystkich miast w bazie.
	 * @throws RemoteException
	 */
	List<City> getAllCities() throws RemoteException;

	/**
	 * Zwraca obiekt miasta o podanej nazwie.
	 * 
	 * @param name
	 *            Nazwa miasta
	 * @return obiekt miasta.
	 * @throws RemoteException
	 */
	City getCityByName(String name) throws RemoteException;
}
