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
 * 
 * Klasa odzwierciedlaj�ca tabel� Mieszka�.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Flat")
@SequenceGenerator(name = "FlatSequence", sequenceName = "FlatSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "getFlatsCountForBuilding", query = "SELECT Count(f) From Flat as f, Building AS b WHERE b = :building and f member of b.flats "),
		@NamedQuery(name = "getFlatsForBuilding", query = "SELECT f FROM Flat f, Building b WHERE b = :building and f member of b.flats"),
		@NamedQuery(name = "getFlatForPerson", query = "SELECT f FROM Flat f, Person p WHERE p = :person AND p MEMBER OF f.persons"),
		@NamedQuery(name = "getAllFlatsWithBuilding", query = "FROM Flat f LEFT JOIN FETCH f.building") })
public class Flat implements Serializable {

	private static final long serialVersionUID = -2960805260506875129L;

	private Long flatId;

	private String flatNumber;

	private Integer rooms;

	private String description;

	private Boolean separateToilet;

	private Boolean balcony;

	private Double metters;

	private List<Person> persons;

	private Rent rent;

	private Person person;

	private Building building;

	/**
	 * Konstruktor. Tworzy instancj� obiektu
	 */
	public Flat() {
		balcony = Boolean.FALSE;
		separateToilet = Boolean.FALSE;
	}

	@Id
	@GeneratedValue(generator = "FlatSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "flat_id")
	public Long getFlatId() {
		return flatId;
	}

	@Column(name = "flat_number")
	public String getFlatNumber() {
		return flatNumber;
	}

	@Column(name = "rooms")
	public Integer getRooms() {
		return rooms;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@Column(name = "separate_toilet")
	public Boolean isSeparateToilet() {
		return separateToilet;
	}

	@Column(name = "balcony")
	public Boolean isBalcony() {
		return balcony;
	}

	@Column(name = "metters")
	public Double getMetters() {
		return metters;
	}

	@OneToMany(mappedBy = "flat", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	public List<Person> getPersons() {
		return persons;
	}

	@OneToOne(mappedBy = "flat")
	public Rent getRent() {
		return rent;
	}

	/**
	 * Zwraca warto�� pola person
	 * 
	 * @return Warto�� pola person
	 */
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "mainTenant")
	public Person getPerson() {
		return person;
	}

	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSeparateToilet(Boolean separateToilet) {
		this.separateToilet = separateToilet;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}

	public void setMetters(Double metters) {
		this.metters = metters;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	/**
	 * Ustawia warto�� pola person
	 * 
	 * @param person
	 *            Nowa warto�� pola person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Flat)) {
			Flat f = (Flat) obj;
			if ((this.getFlatId() != null)
					&& this.getFlatId().equals(f.getFlatId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getFlatId() != null ? getFlatId().hashCode() : 0;
	}

	/**
	 * Zwraca warto�� pola building
	 * 
	 * @return Warto�� pola building
	 */
	@ManyToOne
	@JoinColumn(name = "building_id")
	public Building getBuilding() {
		return building;
	}

	/**
	 * Ustawia warto�� pola building
	 * 
	 * @param building
	 *            Nowa warto�� pola building
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}
}
