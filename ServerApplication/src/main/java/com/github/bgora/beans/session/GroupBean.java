package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.BMSGroup;
import com.github.bgora.beans.entity.constants.GroupNames;
import com.github.bgora.beans.session.bussiness.GroupBeanService;
import com.github.bgora.beans.session.local.GroupBeanLocal;
import com.github.bgora.beans.session.remote.GroupBeanRemote;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Session Bean implementation class GroupBean
 */
@Stateless
@Local( { GroupBeanService.class })
public class GroupBean implements GroupBeanRemote, GroupBeanLocal,
		GroupBeanService {

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public BMSGroup findById(Long id) throws RemoteException {
		return entityManager.find(BMSGroup.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(BMSGroup entity) throws RemoteException {

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(BMSGroup entity) throws RemoteException {
		if (entity.getId() != null) {
			entityManager.merge(entity);
		} else {
			entityManager.persist(entity);
		}
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.GroupBeanService#findByName(java.lang.String)
	 */
	@Override
	public BMSGroup findByName(String groupName) throws RemoteException {
		GroupNames name = GroupNames.valueOf(groupName);
		return findByGroupName(name);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.GroupBeanService#getGroups()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BMSGroup> getGroups() throws RemoteException {
		Query q = entityManager.createNamedQuery("getGroups");
		return q.getResultList();
	}

	@Override
	public BMSGroup findByGroupName(GroupNames name) throws RemoteException {
		Query q = entityManager.createNamedQuery("getGroupCountByName");
		q.setParameter("groupName", name);
		Long count = (Long) q.getSingleResult();
		if (count > 0) {
			q = entityManager.createNamedQuery("getGroupByName");
			q.setParameter("groupName", name);
			return (BMSGroup) q.getSingleResult();
		}
		return null;
	}
}
