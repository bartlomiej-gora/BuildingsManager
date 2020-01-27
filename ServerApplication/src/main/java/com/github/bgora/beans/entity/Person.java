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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Klasa implemetuj�ca tabel� os�b z bazy danych.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Person")
@SequenceGenerator(sequenceName = "PersonSequence", name = "PersonSequence", allocationSize = 1, initialValue = 0)
@NamedQueries(value = {
		@NamedQuery(name = "getPersonsWithFlats", query = "Select p From Person p LEFT JOIN FETCH p.flat WHERE p.name like :name AND p.surname like :surname"),
		@NamedQuery(name = "getPersonByIdCount", query = "Select Count (p) From Person p WHERE p.personId = :id"),
		@NamedQuery(name = "getPersonById", query = "SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.user LEFT JOIN FETCH p.flat WHERE p.personId = :id") })
public class Person implements Serializable {

	private static final long serialVersionUID = -5446631119751015852L;

	private Long personId;

	private String name;

	private String surname;

	private String pesel;

	private User user;

	private Flat flat;

	private Boolean removed;

	@Id
	@GeneratedValue(generator = "PersonSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "person_id")
	public Long getPersonId() {
		return personId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	@Column(name = "pesel")
	public String getPesel() {
		return pesel;
	}

	@OneToOne(mappedBy = "person", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	public User getUser() {
		return user;
	}

	/**
	 * Zwraca warto�� pola flat
	 * 
	 * @return Warto�� pola flat
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "flat_id")
	public Flat getFlat() {
		return flat;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Ustawia warto�� pola flat
	 * 
	 * @param flat
	 *            Nowa warto�� pola flat
	 */
	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Person) {
			Person other = (Person) obj;
			if (other.getPersonId() != null
					&& other.getPersonId().equals(getPersonId())) {
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
		return getPersonId().hashCode();
	}

	/**
	 * Zwraca warto�� pola removed
	 * 
	 * @return Warto�� pola removed
	 */
	@Column(name = "removed")
	public Boolean getRemoved() {
		return removed;
	}

	/**
	 * Ustawia warto�� pola removed
	 * 
	 * @param removed
	 *            Nowa warto�� pola removed
	 */
	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
}
