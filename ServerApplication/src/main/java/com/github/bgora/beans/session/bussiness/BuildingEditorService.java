/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfejs dla beana stanowego do edycji budynk�w
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface BuildingEditorService {

	/**
	 * Ustawia budynek do zapisu w bazie dla tego beana stanowego.
	 * 
	 * @param building
	 *            Budynek do zapisu
	 * @throws RemoteException
	 */
	public void setBuiding(Building building) throws RemoteException;

	/**
	 * Zwraca obiekt budynku.
	 * 
	 * @return obiekt klasy Building
	 * @throws RemoteException
	 */
	Building getBuilding() throws RemoteException;

	/**
	 * Ustawia list� mieszka� dla wybranego budynku.
	 * 
	 * @param flats
	 *            lista mieszka�.
	 * @throws RemoteException
	 */
	void setFlats(List<Flat> flats) throws RemoteException;

	/**
	 * Zwraca list� mieszka� dla edytowanego budynku.
	 * 
	 * @return lista mieszka�.
	 * @throws RemoteException
	 */
	List<Flat> getFlats() throws RemoteException;

	/**
	 * Zapisuje zmiany do bazy danych.
	 * 
	 * @throws RemoteException
	 */
	void commit() throws RemoteException;

	/**
	 * Anuluje zmiany i ko�czy transakcj�.
	 * 
	 * @throws RemoteException
	 */
	void cancel() throws RemoteException;

	/**
	 * Usuwa mieszkanie.
	 * 
	 * @param flat
	 *            mieszkanie do usuni�cia.
	 * @throws RemoteException
	 */
	void removeFlat(Flat flat) throws RemoteException;
}
