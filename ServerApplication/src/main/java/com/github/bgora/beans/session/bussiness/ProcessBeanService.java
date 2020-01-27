/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.User;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfejs biznesowy dla bean�w Proces�w
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface ProcessBeanService extends AbstractBeanService<MainProcess> {

	/**
	 * Zwraca list� proces�w biznesowych
	 * 
	 * @return lista proces�w.
	 * @throws RemoteException
	 */
	List<MainProcess> listProccessTemplates() throws RemoteException;

	/**
	 * Zwraca list� proces�w dla podanego u�ytkownika.
	 * 
	 * @param user
	 *            u�ytklownik
	 * @return Lista proces�w.
	 * @throws RemoteException
	 */
	List<MainProcess> listActualProcessesForUser(User user)
			throws RemoteException;

	/**
	 * Akceptuje proces i wysy�a do nast�pnego kroku.
	 * 
	 * @param proccess
	 *            proces
	 * @throws RemoteException
	 */
	void acceptProccess(MainProcess proccess) throws RemoteException;

	/**
	 * Odrzuca proces i kieruje do kroku odrzucenia.
	 * 
	 * @param proccess
	 *            proces
	 * @throws RemoteException
	 */
	void refuseProccess(MainProcess proccess) throws RemoteException;

	/**
	 * Zwraca proces do poprzedniego u�ytkownika.
	 * 
	 * @param process
	 * @throws RemoteException
	 */
	void returnProccess(MainProcess process) throws RemoteException;

	/**
	 * Zwraca proces po ID.
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	MainProcess getById(Long id) throws RemoteException;
}
