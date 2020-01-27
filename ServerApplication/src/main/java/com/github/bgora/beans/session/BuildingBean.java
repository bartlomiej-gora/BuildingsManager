package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.City;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.local.BuildingBeanLocal;
import com.github.bgora.beans.session.remote.BuildingBeanRemote;
import com.github.bgora.utils.RelationOperator;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class BuildingBean
 */
@Stateless(name = "BuildingBean")
@Clustered
public class BuildingBean implements BuildingBeanRemote, BuildingBeanLocal {
	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public BuildingBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#findById(java.lang.Long)
	 */
	@Override
	public Building findById(Long id) throws RemoteException {
		return entityManager.find(Building.class, id);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#save(java.lang.Object)
	 */
	@Override
	public void save(Building entity) throws RemoteException {
		entityManager.persist(entity);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingBeanService#getBuildings(java.lang.String,
	 *      java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBuildings(String streetName, Long flatCount,
			RelationOperator operator) throws RemoteException {
		Query q = entityManager.createNamedQuery("getFilteredBuildings");
		q.setParameter("streetName", streetName + "%");
		List<Building> buildings = q.getResultList();
		q = entityManager.createNamedQuery("getFlatsCountForBuilding");
		List<Object[]> objects = new ArrayList<Object[]>();
		for (Building building : buildings) {
			q.setParameter("building", building);
			Long flatsCount = (Long) q.getSingleResult();
			Object[] object = new Object[] { building, flatsCount };
			objects.add(object);
		}
		if (flatCount != null && operator != null) {
			filterByFlatCount(objects, operator, flatCount);
		}

		return objects;
	}

	/**
	 * Metoda pomocnicza session beana do filtrowania wed�ug zadanego wrunku
	 * 
	 * @param objects
	 *            lista obiekt�w Object[] (Building, Long)
	 * @param flatCount
	 *            liczba mieszka�
	 * @param operatorOperator
	 *            wed�ug jakiego ma by� sortowane.
	 */
	private void filterByFlatCount(List<Object[]> objects,
			RelationOperator operator, Long flatCount) {
		for (Object[] object : objects) {
			if (!meetsOperator(object, operator, flatCount)) {
				objects.remove(object);
			}
		}
	}

	/**
	 * Sprawdza, cyz dany obiekt spe�nia podany warunek.
	 * 
	 * @param object
	 *            obiekt do sprawdzenia
	 * @param operator
	 *            operator dla warunku
	 * @param flatCount
	 *            liczba mieszka�
	 * @return <code>true</code> je�li spe�nia warunek <code>false</code> w
	 *         przeciwnym wypadku
	 */
	private boolean meetsOperator(Object[] object, RelationOperator operator,
			Long flatCount) {
		if ((operator.equals(RelationOperator.EQUAL))
				&& ((Long) object[1] == flatCount)) {
			return true;
		} else if ((operator.equals(RelationOperator.GREATEROREQUALTHEN))
				&& ((Long) object[1] >= flatCount)) {
			return true;
		} else if ((operator.equals(RelationOperator.GREATERTHEN))
				&& ((Long) object[1] > flatCount)) {
			return true;
		} else if ((operator.equals(RelationOperator.LESSOREQUALTHEN))
				&& ((Long) object[1]) <= flatCount) {
			return true;
		} else if ((operator.equals(RelationOperator.LESSTHAN))
				&& ((Long) object[1]) < flatCount) {
			return true;
		} else if ((operator.equals(RelationOperator.NONEQUAL))
				&& ((Long) object[1]) != flatCount) {
			return true;
		}
		return false;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.AbstractBeanService#remove(java.lang.Object)
	 */
	@Override
	public void remove(Building entity) throws RemoteException {
		entity = entityManager.merge(entity);
		City city = entity.getCity();
		city.getBuildings().remove(entity);
		entityManager.merge(city);
		entityManager.remove(entity);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingBeanService#getBuildingForFlat(com.github.bgora.beans.entity.Flat)
	 */
	@Override
	public Building getBuildingForFlat(Flat flat) throws RemoteException {
		Query q = entityManager.createNamedQuery("getBuildingForFlat");
		q.setParameter("flat", flat);
		return (Building) q.getSingleResult();
	}

}
