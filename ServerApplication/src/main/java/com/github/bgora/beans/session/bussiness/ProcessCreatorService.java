/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.MainProcess;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.entity.constants.ProcessName;

import java.rmi.RemoteException;

/**
 * 
 * Interfejs biznesowy dla kreatora Proces�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface ProcessCreatorService {

	/**
	 * Zwraca aktualnie przechowywany proces.
	 * 
	 * @return aktualnie przechowywany proces
	 * @throws RemoteException
	 */
	MainProcess getProccess() throws RemoteException;

	/**
	 * Stw�rz proces.
	 * 
	 * @param procName
	 *            nazwa procesu, jaki ma by� stworzony
	 * @param author
	 *            U�ytkownik b�d�cy autorem procesu.
	 * @throws RemoteException
	 */
	void createProccess(ProcessName procName, User author)
			throws RemoteException;

	/**
	 * Uruchamia process biznesowy
	 * 
	 * @param content
	 *            tre�� wniosku
	 * @throws RemoteException
	 */
	void startProccess(String content) throws RemoteException;

	/**
	 * 
	 * @throws RemoteException
	 */
	void cancel() throws RemoteException;
}
