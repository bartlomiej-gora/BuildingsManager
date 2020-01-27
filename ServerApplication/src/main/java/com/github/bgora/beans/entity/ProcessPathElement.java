/**
 * 
 */
package com.github.bgora.beans.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Klasa okre�laj�ca element �cie�ki procesu biznesowego.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "ProcessPathElement")
@SequenceGenerator(name = "ProcessPathSequence", sequenceName = "ProcessPathSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "getNextStep", query = "SELECT pe.nextStep FROM ProcessPathElement pe, MainProcess mp WHERE mp.processName = :procName AND pe.processTemplate = mp AND pe.orderNumber = :number AND pe.status = :status AND pe.actual = FALSE AND mp.actual = FALSE"),
		@NamedQuery(name = "getRefuseStep", query = "SELECT pe.refuseStep FROM ProcessPathElement pe, MainProcess mp WHERE mp.processName = :procName AND pe.processTemplate = mp AND pe.orderNumber = :number AND pe.status = :status AND pe.actual = FALSE AND mp.actual = FALSE") })
public class ProcessPathElement implements Serializable, Cloneable {

	private Long id;
	private String processPathElementName;
	private String displayName;
	private Integer orderNumber;
	private Boolean actual;
	private MainProcess processTemplate;
	private MainProcess actualProcess;

	private ProcessPathElement prevStep;
	private ProcessPathElement nextStep;
	private ProcessPathElement refuseStep;

	private String userComment;
	private ProposalStatus status;
	private ProposalData content;

	private BMSGroup responsible;
	private User actuallyResponsible;
	private Boolean processed;
	private String placement;
	private Date createDate;

	/**
	 * Zwraca warto�� pola id
	 * 
	 * @return Warto�� pola id
	 */
	@Id
	@Column(name = "process_path_id", nullable = false)
	@GeneratedValue(generator = "ProcessPathSequence", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	/**
	 * Zwraca warto�� pola processPathElementName
	 * 
	 * @return Warto�� pola processPathElementName
	 */
	@Column(name = "processPathElementName")
	public String getProcessPathElementName() {
		return processPathElementName;
	}

	/**
	 * Zwraca warto�� pola displayName
	 * 
	 * @return Warto�� pola displayName
	 */
	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Zwraca warto�� pola orderNumber
	 * 
	 * @return Warto�� pola orderNumber
	 */
	@Column(name = "order_number")
	public Integer getOrderNumber() {
		return orderNumber;
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
	 * Zwraca warto�� pola processTemplate
	 * 
	 * @return Warto�� pola processTemplate
	 */
	@ManyToOne
	@JoinColumn(name = "template_id")
	public MainProcess getProcessTemplate() {
		return processTemplate;
	}

	/**
	 * Zwraca warto�� pola actualProcess
	 * 
	 * @return Warto�� pola actualProcess
	 */
	@ManyToOne
	@JoinColumn(name = "parent_process")
	public MainProcess getActualProcess() {
		return actualProcess;
	}

	/**
	 * Zwraca warto�� pola prevStep
	 * 
	 * @return Warto�� pola prevStep
	 */
	@OneToOne
	@JoinColumn(name = "prevStep")
	public ProcessPathElement getPrevStep() {
		return prevStep;
	}

	/**
	 * Zwraca warto�� pola nextStep
	 * 
	 * @return Warto�� pola nextStep
	 */
	@OneToOne
	@JoinColumn(name = "nextStep")
	public ProcessPathElement getNextStep() {
		return nextStep;
	}

	/**
	 * Zwraca warto�� pola userComment
	 * 
	 * @return Warto�� pola userComment
	 */
	@Column(name = "user_comment")
	public String getUserComment() {
		return userComment;
	}

	/**
	 * Zwraca warto�� pola status
	 * 
	 * @return Warto�� pola status
	 */
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	public ProposalStatus getStatus() {
		return status;
	}

	/**
	 * Zwraca warto�� pola content
	 * 
	 * @return Warto�� pola content
	 */
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "content_id")
	public ProposalData getContent() {
		return content;
	}

	/**
	 * Zwraca warto�� pola responsible
	 * 
	 * @return Warto�� pola responsible
	 */
	@ManyToOne
	@JoinColumn(name = "responsible_group")
	public BMSGroup getResponsible() {
		return responsible;
	}

	/**
	 * Zwraca warto�� pola actuallyResponsible
	 * 
	 * @return Warto�� pola actuallyResponsible
	 */
	@ManyToOne
	@JoinColumn(name = "responsible_user")
	public User getActuallyResponsible() {
		return actuallyResponsible;
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
	 * Ustawia warto�� pola processPathElementName
	 * 
	 * @param processPathElementName
	 *            Nowa warto�� pola processPathElementName
	 */
	public void setProcessPathElementName(String name) {
		this.processPathElementName = name;
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
	 * Ustawia warto�� pola orderNumber
	 * 
	 * @param orderNumber
	 *            Nowa warto�� pola orderNumber
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
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
	 * Ustawia warto�� pola processTemplate
	 * 
	 * @param processTemplate
	 *            Nowa warto�� pola processTemplate
	 */
	public void setProcessTemplate(MainProcess processTemplate) {
		this.processTemplate = processTemplate;
	}

	/**
	 * Ustawia warto�� pola actualProcess
	 * 
	 * @param actualProcess
	 *            Nowa warto�� pola actualProcess
	 */
	public void setActualProcess(MainProcess actualProcess) {
		this.actualProcess = actualProcess;
	}

	/**
	 * Ustawia warto�� pola prevStep
	 * 
	 * @param prevStep
	 *            Nowa warto�� pola prevStep
	 */
	public void setPrevStep(ProcessPathElement prevStep) {
		this.prevStep = prevStep;
	}

	/**
	 * Ustawia warto�� pola nextStep
	 * 
	 * @param nextStep
	 *            Nowa warto�� pola nextStep
	 */
	public void setNextStep(ProcessPathElement nextStep) {
		this.nextStep = nextStep;
	}

	/**
	 * Ustawia warto�� pola userComment
	 * 
	 * @param userComment
	 *            Nowa warto�� pola userComment
	 */
	public void setUserComment(String userComment) {
		this.userComment = userComment;
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
	 * Ustawia warto�� pola content
	 * 
	 * @param content
	 *            Nowa warto�� pola content
	 */
	public void setContent(ProposalData content) {
		this.content = content;
	}

	/**
	 * Ustawia warto�� pola responsible
	 * 
	 * @param responsible
	 *            Nowa warto�� pola responsible
	 */
	public void setResponsible(BMSGroup responsible) {
		this.responsible = responsible;
	}

	/**
	 * Ustawia warto�� pola actuallyResponsible
	 * 
	 * @param actuallyResponsible
	 *            Nowa warto�� pola actuallyResponsible
	 */
	public void setActuallyResponsible(User actuallyResponsible) {
		this.actuallyResponsible = actuallyResponsible;
	}

	/**
	 * Zwraca warto�� pola refuseStep
	 * 
	 * @return Warto�� pola refuseStep
	 */
	@OneToOne
	@JoinColumn(name = "refuse_id")
	public ProcessPathElement getRefuseStep() {
		return refuseStep;
	}

	/**
	 * Ustawia warto�� pola refuseStep
	 * 
	 * @param refuseStep
	 *            Nowa warto�� pola refuseStep
	 */
	public void setRefuseStep(ProcessPathElement refuseStep) {
		this.refuseStep = refuseStep;
	}

	/**
	 * Zwraca warto�� pola processed
	 * 
	 * @return Warto�� pola processed
	 */
	@Column(name = "processed")
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
	 * Ustawia warto�� pola placement
	 * 
	 * @param placement
	 *            Nowa warto�� pola placement
	 */
	public void setPlacement(String placement) {
		this.placement = placement;
	}

	@Override
	public Object clone() {
		ProcessPathElement result = new ProcessPathElement();
		result.setPlacement(getPlacement());
		result.setActual(Boolean.TRUE);
		result.setDisplayName(getDisplayName());
		result.setProcessPathElementName(getProcessPathElementName());
		result.setOrderNumber(getOrderNumber());
		result.setStatus(getStatus());
		result.setProcessTemplate(getProcessTemplate());
		result.setRefuseStep(getRefuseStep());
		result.setResponsible(getResponsible());

		return result;
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
}
