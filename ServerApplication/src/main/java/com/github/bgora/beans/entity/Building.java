/**
 * 
 */
package com.github.bgora.beans.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Building")
@NamedQueries(value = {
		@NamedQuery(name = "getFilteredBuildings", query = "FROM Building WHERE streetName like :streetName"),
		@NamedQuery(name = "getBuildingCountForFlat", query = "Select Count(b) FROM Building b, Flat f WHERE f = :flat AND f member of b.flats"),
		@NamedQuery(name = "getBuildingForFlat", query = "Select b FROM Building b, Flat f Where f = :flat AND f member of b.flats") })
@SequenceGenerator(name = "BuildingSequence", sequenceName = "BuildingSequence", initialValue = 0, allocationSize = 1)
public class Building implements Serializable {

	private static final long serialVersionUID = -4881793390180862510L;

	private Long buildingId;

	private String streetName;

	private String streetNumber;

	private List<Flat> flats;

	private Pricing pricing;

	private City city;

	@Id
	@GeneratedValue(generator = "BuildingSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "building_id")
	public Long getBuildingId() {
		return buildingId;
	}

	@Column(name = "street_name")
	public String getStreetName() {
		return streetName;
	}

	@Column(name = "street_number")
	public String getStreetNumber() {
		return streetNumber;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "building_id")
	public List<Flat> getFlats() {
		return flats;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "building_id")
	public Pricing getPricing() {
		return pricing;
	}

	/**
	 * Zwraca warto�� pola city
	 * 
	 * @return Warto�� pola city
	 */
	@ManyToOne
	@JoinColumn(name = "city_id")
	public City getCity() {
		return city;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setFlats(List<Flat> flats) {
		this.flats = flats;
	}

	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

	/**
	 * Ustawia warto�� pola city
	 * 
	 * @param city
	 *            Nowa warto�� pola city
	 */
	public void setCity(City city) {
		this.city = city;
	}
}
