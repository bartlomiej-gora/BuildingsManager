package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.User;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfejs biznesowy dla Servicu u�ytkownik�w.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface UserBeanService extends AbstractBeanService<User> {

	/**
	 * Metoda s�u��ca do logowania. Je�li u�ytkownik poda� poprawn� nazw� i
	 * has�o, to zwr�coony zostanie obiekt User. Je�li u�ytkownik poda� b�dne
	 * dane, lub u�tykownik o takich danych nie istenieje w systemie, to metoda
	 * zwr�ci null.
	 * 
	 * @param login
	 *            Login u�ytkownika.
	 * @param passwd
	 *            Has�o u�ytkownika.
	 * @return Obiekt klasy User, lub null.
	 */
	User login(String login, String passwd);

	/**
	 * Metoda wykorzystywana do inicjalizacji bazy. Wype�nia baz� rolami, oraz
	 * u�ytkownikiem administratora.
	 * 
	 * Implementowana jest w beanie do zarz�dzania u�ytkownikami ze wzgl�du na
	 * kolejno�� sprawdzenia. Najpierw sprawdzamy, czy istniej� wymagane role,
	 * p�niej, czy s� obecni wymagani u�ytkownicy
	 * 
	 * @throws RemoteException
	 */
	void initData() throws RemoteException;

	/**
	 * Zwraca liste u�ytkownik�w z bazy danych.
	 * 
	 * Parametry wejsciowe s�u�� do filtrowania.
	 * 
	 * @param login
	 *            Filtr loginu
	 * @param name
	 *            Filtr imienia
	 * @param surname
	 *            Filtr nazwiska
	 * @return Lista obiekt�w klasy user
	 * @throws RemoteException
	 */
	List<User> getUsersList(String login, String name, String surname)
			throws RemoteException;

	/**
	 * @param id
	 *            Id szukanego u�ytkownika.
	 * @return Szukany u�ytkownik.
	 * @throws RemoteException
	 */
	User getUserById(Long id) throws RemoteException;

	/**
	 * Zwraca u�ytkownika po loginie.
	 * 
	 * @param login
	 *            login szukanego u�ytkownika.
	 * @return Uzytkownik
	 * @throws RemoteException
	 */
	User getUserByLogin(String login) throws RemoteException;

}
