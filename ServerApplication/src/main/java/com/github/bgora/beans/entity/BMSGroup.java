/**
 * 
 */
package com.github.bgora.beans.entity;

import com.github.bgora.beans.entity.constants.GroupNames;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Klasa okre�laj�ca grup�
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "BMSGroup")
@SequenceGenerator(name = "GroupSequence", sequenceName = "GroupSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "getGroupCountByName", query = "SELECT COUNT(g) FROM BMSGroup g WHERE g.groupName = :groupName"),
		@NamedQuery(name = "getGroupCount", query = "SELECT COUNT(g) FROM BMSGroup g"),
		@NamedQuery(name = "getGroupByName", query = "FROM BMSGroup g WHERE g.groupName = :groupName"),
		@NamedQuery(name = "getGroups", query = "FROM BMSGroup") })
public class BMSGroup implements Serializable {

	private List<User> users;

	private GroupNames groupName;

	private Long id;

	private String displayName;

	private List<ProcessPathElement> pathes;

	/**
	 * Zwraca warto�� pola users
	 * 
	 * @return Warto�� pola users
	 */
	@ManyToMany(mappedBy = "groups")
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Zwraca warto�� pola groupName
	 * 
	 * @return Warto�� pola groupName
	 */
	@Enumerated(value = EnumType.STRING)
	public GroupNames getGroupName() {
		return groupName;
	}

	/**
	 * Zwraca warto�� pola id
	 * 
	 * @return Warto�� pola id
	 */
	@Id
	@GeneratedValue(generator = "GroupSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "group_id", nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * Zwraca warto�� pola displayName
	 * 
	 * @return Warto�� pola displayName
	 */
	@Column(name = "displayName")
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Ustawia warto�� pola users
	 * 
	 * @param users
	 *            Nowa warto�� pola users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Ustawia warto�� pola groupName
	 * 
	 * @param groupName
	 *            Nowa warto�� pola groupName
	 */
	public void setGroupName(GroupNames name) {
		this.groupName = name;
	}

	/**
	 * Ustawia warto�� pola id
	 * 
	 * @param id
	 *            Nowa warto�� pola id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Ustawia warto�� pola displayName
	 * 
	 * @param displayName
	 *            Nowa warto�� pola displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Zwraca warto�� pola pathes
	 * 
	 * @return Warto�� pola pathes
	 */
	@OneToMany(mappedBy = "responsible")
	public List<ProcessPathElement> getPathes() {
		return pathes;
	}

	/**
	 * Ustawia warto�� pola pathes
	 * 
	 * @param pathes
	 *            Nowa warto�� pola pathes
	 */
	public void setPathes(List<ProcessPathElement> pathes) {
		this.pathes = pathes;
	}

	@Override
	public String toString() {
		return groupName.name();
	}
}
