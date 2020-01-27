/**
 * 
 */
package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.constants.GroupNames;

import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * Service dla bean�w bezstanowych dla Grup.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
public interface GroupBeanService extends AbstractBeanService<BMSGroup> {

	/**
	 * Znajduje Grupe po nazwie.
	 * 
	 * @param groupName
	 *            nazwa grupy
	 * @return grupa
	 * @throws RemoteException
	 */
	BMSGroup findByName(String groupName) throws RemoteException;

	/**
	 * Zwraca list� grup.
	 * 
	 * @return lista wszytskich grup
	 * @throws RemoteException
	 */
	List<BMSGroup> getGroups() throws RemoteException;

	BMSGroup findByGroupName(GroupNames name) throws RemoteException;
}
