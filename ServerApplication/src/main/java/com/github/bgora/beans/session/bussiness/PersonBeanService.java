/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Person;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Bart�omiej G�ra
 * 
 */
public interface PersonBeanService extends AbstractBeanService<Person> {

	/**
	 * Metoda zwraca przefiltrowan� list� mieszkanc�w.
	 * 
	 * @param name
	 *            imie
	 * @param surname
	 *            nazwisko
	 * @param street
	 *            nazwa ulicy.
	 * @return lista mieszka�c�w.
	 * @throws RemoteException
	 *             Zdalny wyj�tek.
	 */
	List<Person> getPersonList(String name, String surname, String street)
			throws RemoteException;

	/**
	 * Pobiera obiekt Person wraz ze wszytskimi zale�no�ciami potrzebnymi do
	 * edycji obiektu.
	 * 
	 * @param id
	 *            id obiektu Person.
	 * @return znaleziony egzemplarz obiektu Person.
	 * @throws RemoteException
	 *             Zdalny wyj�tek.
	 */
	Person getPersonById(Long id) throws RemoteException;
}
