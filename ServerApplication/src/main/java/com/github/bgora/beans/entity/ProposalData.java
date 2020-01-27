/**
 * 
 */
package com.github.bgora.beans.entity;

import com.github.bgora.beans.entity.constants.ProposalStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Obiekt wniosku. Zawiera dane wniosku wysy�anego przez klienta. I Kolejne dane
 * odpowiedzi i komentarze.
 * 
 * @author Bart�omiej G�ra (Black007pl@gmail.com)
 * 
 */
@Entity
@Table(name = "ProposalData")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@SequenceGenerator(name = "ProsposalDataSequence", sequenceName = "ProposalDataSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = { @NamedQuery(name = "getContentForProcessTemplate", query = "SELECT c FROM ProposalData c WHERE mainProcess = :proccess") })
public class ProposalData implements Serializable, Cloneable {

	private Long id;
	private String proposalDataName;
	private String displayName;
	private Date createDate;
	private User author;
	private Boolean actual;
	private ProcessPathElement actualPathElement;
	private MainProcess mainProcess;
	private List<ProcessPathElement> proposalHistory;
	private ProposalStatus status;
	private String content;

	/**
	 * Zwraca warto�� pola id
	 * 
	 * @return Warto�� pola id
	 */
	@Id
	@GeneratedValue(generator = "ProsposalDataSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "prosposal_data_id", nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * Zwraca warto�� pola proposalDataName
	 * 
	 * @return Warto�� pola proposalDataName
	 */
	@Column(name = "proposalDataName")
	public String getProposalDataName() {
		return proposalDataName;
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
	 * Zwraca warto�� pola createDate
	 * 
	 * @return Warto�� pola createDate
	 */
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Zwraca warto�� pola author
	 * 
	 * @return Warto�� pola author
	 */
	@ManyToOne
	@JoinColumn(name = "author")
	public User getAuthor() {
		return author;
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
	 * Zwraca warto�� pola actualPathElement
	 * 
	 * @return Warto�� pola actualPathElement
	 */
	@OneToOne(mappedBy = "content")
	public ProcessPathElement getActualPathElement() {
		return actualPathElement;
	}

	/**
	 * Zwraca warto�� pola mainProcess
	 * 
	 * @return Warto�� pola mainProcess
	 */
	@ManyToOne
	@JoinColumn(name = "main_process")
	public MainProcess getMainProcess() {
		return mainProcess;
	}

	/**
	 * Zwraca warto�� pola proposalHistory
	 * 
	 * @return Warto�� pola proposalHistory
	 */
	@OneToMany
	@JoinColumn(name = "history")
	public List<ProcessPathElement> getProposalHistory() {
		return proposalHistory;
	}

	/**
	 * Zwraca warto�� pola status
	 * 
	 * @return Warto�� pola status
	 */
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	public ProposalStatus getStatus() {
		return status;
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
	 * Ustawia warto�� pola proposalDataName
	 * 
	 * @param proposalDataName
	 *            Nowa warto�� pola proposalDataName
	 */
	public void setProposalDataName(String name) {
		this.proposalDataName = name;
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
	 * Ustawia warto�� pola createDate
	 * 
	 * @param createDate
	 *            Nowa warto�� pola createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Ustawia warto�� pola author
	 * 
	 * @param author
	 *            Nowa warto�� pola author
	 */
	public void setAuthor(User author) {
		this.author = author;
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
	 * Ustawia warto�� pola actualPathElement
	 * 
	 * @param actualPathElement
	 *            Nowa warto�� pola actualPathElement
	 */
	public void setActualPathElement(ProcessPathElement actualPathElement) {
		this.actualPathElement = actualPathElement;
	}

	/**
	 * Ustawia warto�� pola mainProcess
	 * 
	 * @param mainProcess
	 *            Nowa warto�� pola mainProcess
	 */
	public void setMainProcess(MainProcess mainProcess) {
		this.mainProcess = mainProcess;
	}

	/**
	 * Ustawia warto�� pola proposalHistory
	 * 
	 * @param proposalHistory
	 *            Nowa warto�� pola proposalHistory
	 */
	public void setProposalHistory(List<ProcessPathElement> proposalHistory) {
		this.proposalHistory = proposalHistory;
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
	 * Zwraca warto�� pola content
	 * 
	 * @return Warto�� pola content
	 */
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	/**
	 * Ustawia warto�� pola content
	 * 
	 * @param content
	 *            Nowa warto�� pola content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Nadpisanie metody z klasy bazowej Przypomnienie z SCJP, je�li klasa
	 * przykrywa metod� klasy nadrz�dnej, kt�re rzuca Checked Excpetion, to
	 * metoda przykrywahj�ca nie musi zg�asza� tego wyj�tku. Dzi�ki temu nie
	 * trzeba wstawia� try{}catch
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		ProposalData result = new ProposalData();
		result.setContent(getContent());
		result.setAuthor(getAuthor());
		result.setCreateDate(getCreateDate());
		return result;
	}
}
