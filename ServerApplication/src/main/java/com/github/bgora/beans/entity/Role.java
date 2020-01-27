/**
 * 
 */
package com.github.bgora.beans.entity;

import com.github.bgora.beans.entity.constants.RoleTypes;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Klasa implemetuj�ca tabel� R�l z bazy danych.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "getAllRolesCount", query = "Select Count(r) From Role r"),
		@NamedQuery(name = "getAllRoles", query = " From Role"),
		@NamedQuery(name = "getRoleByName", query = "From Role WHERE role = :role") })
@Table(name = "Role")
@SequenceGenerator(name = "RoleSequence", allocationSize = 1, initialValue = 0, sequenceName = "RoleSequence")
public class Role implements Serializable {

	private static final long serialVersionUID = 7362334666321293987L;

	private Long roleId;

	private RoleTypes role;

	@Id
	@GeneratedValue(generator = "RoleSequence", strategy = GenerationType.SEQUENCE)
	public Long getRoleId() {
		return roleId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	public RoleTypes getRole() {
		return role;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRole(RoleTypes role) {
		this.role = role;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getRole().name();
	}
}
