package com.github.bgora.beans.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa odzworowuj�ca tabel� u�ytkownik�w.
 * 
 * @see javax.persistence.Entity
 * @see javax.persistence.SequenceGenerator
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Users")
@SequenceGenerator(name = "UserSequence", initialValue = 0, allocationSize = 1, sequenceName = "UserSequence")
@NamedQueries(value = {
		@NamedQuery(name = "loginQuery", query = "From User u LEFT JOIN FETCH u.role WHERE u.login = :login AND u.passwd = :passwd"),
		@NamedQuery(name = "getAdminUserCount", query = "Select Count(u) From User u where login = \'admin\' "),
		@NamedQuery(name = "getLoginCount", query = "Select Count(u) From User u Where u.login = :login AND u.passwd = :passwd"),
		@NamedQuery(name = "getUsersWithPersonCountFiltered", query = "SELECT Count (u) FROM User u, Person p WHERE u.login like :login AND p.name like :name AND p.surname like :surname "),
		@NamedQuery(name = "getUsersWithPersonFiltered", query = "Select DISTINCT u From User u, Person p, Role r WHERE u.login like :login AND p.name like :name AND p.surname like :surname "),
		@NamedQuery(name = "getUserForPersonCount", query = "SELECT COUNT (u) FROM User u WHERE u.person = :person"),
		@NamedQuery(name = "getUserForPerson", query = "SELECT u FROM User u WHERE u.person = :person"),
		@NamedQuery(name = "getUserByIdCount", query = "SELECT Count(u) FROM User u WHERE u.userId = :id"),
		@NamedQuery(name = "getUserById", query = "SELECT DISTINCT u FROM User u Left Join Fetch u.groups WHERE u.userId = :id"),
		@NamedQuery(name = "getUserByLoginCount", query = "SELECT Count (u) FROM User u WHERE u.login = :login"),
		@NamedQuery(name = "getUserByLogin", query = "FROM User WHERE login = :login") })
@NamedNativeQueries(value = { @NamedNativeQuery(name = "getUsersWithLessJobs", query = "SELECT role_id, user_id, Min(result) as min_jobs FROM (SELECT role_id, user_id, Count(processpathelement.*) as result FROM users left join processpathelement on responsible_user = user_id GROUP BY user_id, role_id)as result_table, role, users_bmsgroups WHERE role_id = roleid AND role_name <> 'CLIENT' AND user_id = groups AND users = :groupID GROUP BY user_id, role_id ORDER BY Min(result)", resultSetMapping = "getUsersWithLessJobsResult") })
@SqlResultSetMapping(name = "getUsersWithLessJobsResult", columns = {
		@ColumnResult(name = "role_id"), @ColumnResult(name = "user_id"),
		@ColumnResult(name = "min_jobs") })
public class User implements Serializable {

	private static final long serialVersionUID = -1776171399753154200L;

	private Long userId;

	private Role role;

	private String login;

	private String passwd;

	private String status;

	private Person person;

	private String email;

	private List<News> news;

	private List<Document> documents;

	private List<Message> sentMessages;

	private List<Message> receivedMessages;

	private List<BMSGroup> groups;

	private List<ProcessPathElement> pathes;

	private List<ProposalData> proposals;

	/**
	 * 
	 * Konstruktor. Tworzy instancj� obiektu
	 */
	public User() {
		groups = new LinkedList<BMSGroup>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	@Column(name = "passwd", nullable = false)
	public String getPasswd() {
		return passwd;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@OneToMany(mappedBy = "user")
	public List<News> getNews() {
		return news;
	}

	@OneToMany(mappedBy = "author")
	public List<Document> getDocuments() {
		return documents;
	}

	@OneToMany(mappedBy = "from")
	public List<Message> getSentMessages() {
		return sentMessages;
	}

	@OneToMany(mappedBy = "to")
	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	@Column(name = "login", unique = true)
	public String getLogin() {
		return login;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return getLogin();
	}

	/**
	 * Zwraca warto�� pola groups
	 * 
	 * @return Warto�� pola groups
	 */
	@ManyToMany
	@JoinTable(name = "users_bmsgroups", joinColumns = { @JoinColumn(name = "groups") }, inverseJoinColumns = { @JoinColumn(name = "users") })
	public List<BMSGroup> getGroups() {
		return groups;
	}

	/**
	 * Ustawia warto�� pola groups
	 * 
	 * @param groups
	 *            Nowa warto�� pola groups
	 */
	public void setGroups(List<BMSGroup> groups) {
		this.groups = groups;
	}

	/**
	 * Zwraca warto�� pola pathes
	 * 
	 * @return Warto�� pola pathes
	 */
	@OneToMany(mappedBy = "actuallyResponsible")
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

	/**
	 * Zwraca warto�� pola proposals
	 * 
	 * @return Warto�� pola proposals
	 */
	@OneToMany(mappedBy = "author")
	public List<ProposalData> getProposals() {
		return proposals;
	}

	/**
	 * Ustawia warto�� pola proposals
	 * 
	 * @param proposals
	 *            Nowa warto�� pola proposals
	 */
	public void setProposals(List<ProposalData> proposals) {
		this.proposals = proposals;
	}

}
