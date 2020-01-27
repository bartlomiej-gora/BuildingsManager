/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.entity.User;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Bart�omiej G�ra
 * 
 */
public interface MessageBeanService extends AbstractBeanService<Message> {

	/**
	 * 
	 * @param u
	 * @return
	 * @throws RemoteException
	 */
	List<Message> listReceivedMessages(User u) throws RemoteException;

	/**
	 * @param u
	 * @return
	 * @throws RemoteException
	 */
	List<Message> listSentMessages(User u) throws RemoteException;

	/**
	 * @param id
	 *            id
	 * @return Wiadomo��
	 * @throws RemoteException
	 */
	Message getById(Long id) throws RemoteException;
}
