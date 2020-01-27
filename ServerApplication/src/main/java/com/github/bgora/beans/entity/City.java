/**
 * 
 */
package com.github.bgora.beans.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Klasa pomocnicza okre�la Miasto w kt�ym jest budynek. U�ywana, aby m�c
 * okre�li� ca�kowity adres. Gdybyfirma mia�a mieszkaniaw kilku miastach.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "City")
@SequenceGenerator(name = "CitySequenceGenerator", sequenceName = "CitySequenceGenerator", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "getAllCities", query = "From City"),
		@NamedQuery(name = "getCityByNameCount", query = "Select Count (c) From City c WHERE c.name = :name "),
		@NamedQuery(name = "getCityByName", query = "FROM City Where name = :name") })
public class City implements Serializable {

	private static final long serialVersionUID = -5413698280563699928L;

	private Long cityId;

	private String name;

	private List<Building> buildings;

	/**
	 * Zwraca warto�� pola cityId
	 * 
	 * @return Warto�� pola cityId
	 */
	@Id
	@GeneratedValue(generator = "CitySequenceGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}

	/**
	 * Zwraca warto�� pola name
	 * 
	 * @return Warto�� pola name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Zwraca warto�� pola buildings
	 * 
	 * @return Warto�� pola buildings
	 */
	@OneToMany(mappedBy = "city")
	public List<Building> getBuildings() {
		return buildings;
	}

	/**
	 * Ustawia warto�� pola cityId
	 * 
	 * @param cityId
	 *            Nowa warto�� pola cityId
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * Ustawia warto�� pola name
	 * 
	 * @param name
	 *            Nowa warto�� pola name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ustawia warto�� pola buildings
	 * 
	 * @param buildings
	 *            Nowa warto�� pola buildings
	 */
	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	@Override
	public String toString() {
		return getName();
	}
}
