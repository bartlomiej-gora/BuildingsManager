/**
 * 
 */
package com.github.bgora.beans.entity;

import com.github.bgora.beans.entity.constants.ProcessName;
import com.github.bgora.beans.entity.constants.ProposalStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa przedstawiaj�ca tabelk� z procesem.
 * 
 * Definicja obiegu dokument�w.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "MainProcess")
@SequenceGenerator(sequenceName = "ProcessSequence", name = "ProcessSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "getProcessTemplatesCount", query = "SELECT COUNT (p) FROM MainProcess p WHERE p.actual = FALSE"),
		@NamedQuery(name = "getProcessTemplateByName", query = "SELECT DISTINCT p FROM MainProcess p LEFT JOIN FETCH p.templatePathes t WHERE p.actual = FALSE AND p.processName = :procName ORDER BY t.orderNumber"),
		@NamedQuery(name = "getProcessTemplates", query = "SELECT p FROM MainProcess p WHERE p.actual = FALSE"),
		@NamedQuery(name = "getProcessesForUser", query = "SELECT DISTINCT mp FROM MainProcess mp, ProposalData data WHERE data.author = :user AND data.actual = TRUE AND data.mainProcess = mp"),
		@NamedQuery(name = "getProcessesForResponsibleUser", query = "SELECT DISTINCT mp FROM MainProcess mp, ProcessPathElement pe WHERE pe.actual = TRUE AND pe.actuallyResponsible = :user AND pe.actualProcess = mp AND pe.processed = FALSE"),
		@NamedQuery(name = "getProcessById", query = "SELECT DISTINCT mp FROM MainProcess mp LEFT JOIN FETCH mp.actualPathes pathes LEFT JOIN FETCH pathes.content WHERE mp.id = :id order by pathes.orderNumber") })
public class MainProcess implements Serializable {
	private Long id;
	private ProcessName processName;
	private String displayName;
	private List<ProcessPathElement> templatePathes;
	private Boolean actual;
	private List<ProcessPathElement> actualPathes;
	private Date createDate;
	private ProposalStatus status;
	private String placement;
	private Boolean processed;

	public MainProcess() {
		templatePathes = new LinkedList<ProcessPathElement>();
		actualPathes = new LinkedList<ProcessPathElement>();
	}

	/**
	 * Zwraca warto�� pola id
	 * 
	 * @return Warto�� pola id
	 */
	@Id
	@GeneratedValue(generator = "ProcessSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "process_id", nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * Zwraca warto�� pola processName
	 * 
	 * @return Warto�� pola processName
	 */
	@Enumerated(value = EnumType.STRING)
	public ProcessName getProcessName() {
		return processName;
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
	 * Zwraca warto�� pola templatePathes
	 * 
	 * @return Warto�� pola templatePathes
	 */
	@OneToMany(mappedBy = "processTemplate", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	public List<ProcessPathElement> getTemplatePathes() {
		return templatePathes;
	}

	/**
	 * Zwraca warto�� pola actual
	 * 
	 * @return Warto�� pola actual
	 */
	@Column(name = "actual")
	public Boolean getActual() {
		return actual;
	}

	/**
	 * Zwraca warto�� pola actualPathes
	 * 
	 * @return Warto�� pola actualPathes
	 */
	@OneToMany(mappedBy = "actualProcess", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	public List<ProcessPathElement> getActualPathes() {
		return actualPathes;
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
	 * Ustawia warto�� pola processName
	 * 
	 * @param processName
	 *            Nowa warto�� pola processName
	 */
	public void setProcessName(ProcessName name) {
		this.processName = name;
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
	 * Ustawia warto�� pola templatePathes
	 * 
	 * @param templatePathes
	 *            Nowa warto�� pola templatePathes
	 */
	public void setTemplatePathes(List<ProcessPathElement> templatePathes) {
		this.templatePathes = templatePathes;
	}

	/**
	 * Ustawia warto�� pola actual
	 * 
	 * @param actual
	 *            Nowa warto�� pola actual
	 */
	public void setActual(Boolean actual) {
		this.actual = actual;
	}

	/**
	 * Ustawia warto�� pola actualPathes
	 * 
	 * @param actualPathes
	 *            Nowa warto�� pola actualPathes
	 */
	public void setActualPathes(List<ProcessPathElement> actualPathes) {
		this.actualPathes = actualPathes;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getDisplayName();
	}

	/**
	 * Zwraca warto�� pola createDate
	 * 
	 * @return Warto�� pola createDate
	 */
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Ustawia warto�� pola createDate
	 * 
	 * @param createDate
	 *            Nowa warto�� pola createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Zwraca warto�� pola status
	 * 
	 * @return Warto�� pola status
	 */
	@Enumerated(value = EnumType.STRING)
	public ProposalStatus getStatus() {
		return status;
	}

	/**
	 * Zwraca warto�� pola placement
	 * 
	 * @return Warto�� pola placement
	 */
	@Column(name = "placement")
	public String getPlacement() {
		return placement;
	}

	/**
	 * Ustawia warto�� pola status
	 * 
	 * @param status
	 *            Nowa warto�� pola status
	 */
	public void setStatus(ProposalStatus status) {
		this.status = status;
	}

	/**
	 * Ustawia warto�� pola placement
	 * 
	 * @param placement
	 *            Nowa warto�� pola placement
	 */
	public void setPlacement(String placement) {
		this.placement = placement;
	}

	/**
	 * Zwraca warto�� pola processed
	 * 
	 * @return Warto�� pola processed
	 */
	@Transient
	public Boolean getProcessed() {
		return processed;
	}

	/**
	 * Ustawia warto�� pola processed
	 * 
	 * @param processed
	 *            Nowa warto�� pola processed
	 */
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

}
