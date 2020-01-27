package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Role;
import com.github.bgora.beans.entity.constants.RoleTypes;
import com.github.bgora.beans.session.local.RoleBeanLocal;
import com.github.bgora.beans.session.remote.RoleBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Klasa Ziarna sesyjnego s�u��cego do zarz�dzania Rolami w systemie.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Stateless(name = "RoleBean")
@Clustered
public class RoleBean implements RoleBeanRemote, RoleBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public RoleBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Role findById(Long id) throws RemoteException {
		return entityManager.find(Role.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Role entity) throws RemoteException {
		entityManager.persist(entity);

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.RoleBeanService#findRoleByName(java.lang.String)
	 */
	@Override
	public Role findRoleByName(String name) throws RemoteException {
		RoleTypes type = RoleTypes.valueOf(name.toUpperCase());
		return findRoleByType(type);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.RoleBeanService#findRoleByType(com.github.bgora.beans.entity.constants.RoleTypes)
	 */
	@Override
	public Role findRoleByType(RoleTypes type) throws RemoteException {
		Query q = entityManager.createNamedQuery("getRoleByName");
		q.setParameter("role", type);
		return (Role) q.getSingleResult();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.RoleBeanService#getRoles()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() throws RemoteException {
		Query q = entityManager.createNamedQuery("getAllRoles");
		return q.getResultList();
	}

	@Override
	public void remove(Role entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
