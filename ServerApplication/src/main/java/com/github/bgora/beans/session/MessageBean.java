package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Message;
import com.github.bgora.beans.entity.User;
import com.github.bgora.beans.session.local.MessageBeanLocal;
import com.github.bgora.beans.session.remote.MessageBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Session Bean implementation class MessageBean
 */
@Stateless(name = "MessageBean")
@Clustered
public class MessageBean implements MessageBeanRemote, MessageBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Message findById(Long id) throws RemoteException {
		return entityManager.find(Message.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Message entity) throws RemoteException {
		if (entity.getMessageId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @deprecated Nie usuwamy wiadomo�ci mailowych w systemie!
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	@Deprecated
	public void remove(Message entity) throws RemoteException {
		// nie usuwamy wiadomo�cimailowych w systemie.
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.MessageBeanService#listReceivedMessages(com.github.bgora.beans.entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> listReceivedMessages(User u) throws RemoteException {
		Query q = entityManager.createNamedQuery("listReceivedMessages");
		q.setParameter("user", u);
		return q.getResultList();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.MessageBeanService#listSentMessages(com.github.bgora.beans.entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> listSentMessages(User u) throws RemoteException {
		Query q = entityManager.createNamedQuery("listSentMessages");
		q.setParameter("user", u);
		return q.getResultList();
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.MessageBeanService#getById(java.lang.Long)
	 */
	@Override
	public Message getById(Long id) throws RemoteException {
		Message result = null;
		Query q = entityManager.createNamedQuery("getMessageByIDCount");
		q.setParameter("id", id);
		Long count = (Long) q.getSingleResult();
		if (count > 0) {
			q = entityManager.createNamedQuery("getMessageByID");
			q.setParameter("id", id);
			result = (Message) q.getSingleResult();
		}
		return result;
	}
}
