package com.github.bgora.beans.session;

import com.github.bgora.beans.entity.Building;
import com.github.bgora.beans.entity.Flat;
import com.github.bgora.beans.session.local.BuildingEditorBeanLocal;
import com.github.bgora.beans.session.remote.BuildingEditorBeanRemote;
import org.jboss.annotation.ejb.Clustered;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Session Bean implementation class BuildingEditorBean
 */
@Stateful(name = "BuildingEditorBean")
@Clustered
public class BuildingEditorBean implements BuildingEditorBeanRemote,
		BuildingEditorBeanLocal {

	@PersistenceContext(unitName = "bms")
	private EntityManager entityManager;

	private Building building;

	/**
	 * Default constructor.
	 */
	public BuildingEditorBean() {
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingEditorService#commit()
	 */
	@Override
	public void commit() throws RemoteException {
		if (building.getBuildingId() == null) {
			entityManager.persist(building);
		} else {
			entityManager.merge(building);
		}
		building = null;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingEditorService#cancel()
	 */
	@Override
	public void cancel() throws RemoteException {
		this.building = null;

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingEditorService#setBuiding(com.github.bgora.beans.entity.Building)
	 */
	@Override
	public void setBuiding(Building building) throws RemoteException {
		this.building = building;

	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingEditorService#setFlats(java.util.List)
	 */
	@Override
	public void setFlats(List<Flat> flats) throws RemoteException {
		building.setFlats(flats);
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingEditorService#getBuilding()
	 */
	@Override
	public Building getBuilding() throws RemoteException {
		return building;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see com.github.bgora.beans.session.bussiness.BuildingEditorService#getFlats()
	 */
	@Override
	public List<Flat> getFlats() throws RemoteException {
		return building.getFlats();
	}

	@Override
	public void removeFlat(Flat flat) throws RemoteException {
		flat = entityManager.merge(flat);
		building.getFlats().remove(flat);
		entityManager.remove(flat);
		entityManager.merge(building);
	}

}
