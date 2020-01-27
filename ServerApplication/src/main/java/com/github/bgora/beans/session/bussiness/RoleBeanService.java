package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.constants.RoleTypes;

import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface RoleBeanService extends AbstractBeanService<Role> {

	/**
	 * Zwraca Role wed�ug typu w enumie.
	 * 
	 * @param type
	 *            Enum okre�laj�cy typ roli.
	 * @return Rola o podanym typie.
	 * @throws RemoteException
	 */
	Role findRoleByType(RoleTypes type) throws RemoteException;

	/**
	 * Zwraca role o podanej nazwie.
	 * 
	 * @param name
	 *            nazwa roli.
	 * @return Role o podanej nazwie.
	 * @throws RemoteException
	 */
	Role findRoleByName(String name) throws RemoteException;

	/**
	 * Zwraca list� wszytskich r�l w systemie.
	 * 
	 * @return Lista r�l w systemie.
	 * @throws RemoteException
	 */
	List<Role> getRoles() throws RemoteException;
}
