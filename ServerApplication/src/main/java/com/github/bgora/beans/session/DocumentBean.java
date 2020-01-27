package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Document;
import com.github.bgora.beans.session.local.DocumentBeanLocal;
import com.github.bgora.beans.session.remote.DocumentBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Session Bean implementation class DocumentBean
 */
@Stateless(name = "DocumentBean")
@Clustered
public class DocumentBean implements DocumentBeanRemote, DocumentBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Konstruktor. Tworzy instancj� obiektu
	 */
	public DocumentBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Document findById(Long id) throws RemoteException {
		return entityManager.find(Document.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Document entity) throws RemoteException {
		entityManager.persist(entity);

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.DocumentBeanService#getDocuments()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getDocuments() {
		Query q = entityManager.createNamedQuery("getDocuments");
		return q.getResultList(); // NOSONAR
	}

	@Override
	public void remove(Document entity) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
