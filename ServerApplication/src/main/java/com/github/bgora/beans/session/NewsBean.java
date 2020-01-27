package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.News;
import com.github.bgora.beans.session.local.NewsBeanLocal;
import com.github.bgora.beans.session.remote.NewsBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class NewsBean
 */
@Stateless(name = "NewsBean")
@Clustered
public class NewsBean implements NewsBeanRemote, NewsBeanLocal {

	public static final int MAX_RECENT_NEWS = 10;

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public NewsBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public News findById(Long id) throws RemoteException {
		return entityManager.find(News.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.NewsBeanService#getRecentNews()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> getRecentNews() throws RemoteException {
		List<News> news = new ArrayList<News>();
		Query q = entityManager.createNamedQuery("getRecentNews");
		q.setMaxResults(MAX_RECENT_NEWS);
		news = q.getResultList();
		return news;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(News entity) throws RemoteException {
		if (entity.getNewsId() == null) {
			entityManager.persist(entity);
		} else if (entity.getNewsId() != null) {
			entityManager.merge(entity);
		}
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(News entity) throws RemoteException {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}
}
