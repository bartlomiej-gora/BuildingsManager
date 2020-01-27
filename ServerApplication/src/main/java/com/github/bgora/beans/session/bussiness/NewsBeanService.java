package com.github.bgora.beans.session.bussiness;

import com.github.bgora.beans.entity.News;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Bean do zarz�dzania wiadomo�ciami.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public interface NewsBeanService extends AbstractBeanService<News> {

	/**
	 * Zwraca 10 ostatnich wiadmo�ci z systemu.
	 * 
	 * @return lista wiadomo�ci.
	 * @throws RemoteException
	 *             Wyj�tek EJb.
	 */
	List<News> getRecentNews() throws RemoteException;
}
