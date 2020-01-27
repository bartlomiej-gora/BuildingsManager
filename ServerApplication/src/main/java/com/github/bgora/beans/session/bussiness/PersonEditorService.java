package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Person;

/**
 * Interfejs biznesowy dla stanowego ziarna edytora os�b.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface PersonEditorService {

	/**
	 * Ustawia obiekt osoby.
	 * 
	 * @param person
	 *            Obiekt osoby
	 */
	void setPerson(Person person);

	/**
	 * Zwraca obiekt osoby
	 * 
	 * @return
	 */
	Person getPerson();

	/**
	 * Zapisuje zmiany.
	 */
	void commit();

	/**
	 * Anuluje zmiany
	 */
	void cancel();
}
