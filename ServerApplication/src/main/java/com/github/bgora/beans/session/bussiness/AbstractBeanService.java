/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import java.rmi.RemoteException;

/**
 * Interfejs abstrakcyjnego serwisu, zawieraj�cegoi postawowe metody, jakie
 * powinien implementowa� ka�dy session Bean
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface AbstractBeanService<T> {

	/**
	 * Zwraca obiekt Encji wed�ug id.
	 * 
	 * @param id
	 *            id szukanego obiektu.
	 * @return obiekt encji, lub null
	 * @throws RemoteException
	 */
	T findById(Long id) throws RemoteException;

	/**
	 * Zapisuje nowy obiekt w bazie danych
	 * 
	 * @param entity
	 *            entity bean do zapisu.
	 * @throws RemoteException
	 */
	void save(T entity) throws RemoteException;

	/**
	 * Metoda usuwa wskazan� encj� z bazy.
	 * 
	 * @param entity
	 *            encja do usuni�cia.
	 * @throws RemoteException
	 */
	void remove(T entity) throws RemoteException;

}
