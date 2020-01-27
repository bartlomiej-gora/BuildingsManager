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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Klasa odzwierciedlaj�ca wiadmo�� wewn�trznego systemu mailowego.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Message")
@SequenceGenerator(name = "MessageSequence", sequenceName = "MessageSequence", initialValue = 0, allocationSize = 1)
@NamedQueries(value = {
		@NamedQuery(name = "listReceivedMessages", query = "SELECT m FROM Message m LEFT JOIN FETCH m.attachements WHERE m.to = :user ORDER BY m.sendDate DESC"),
		@NamedQuery(name = "listSentMessages", query = "SELECT m FROM Message m LEFT JOIN FETCH m.attachements WHERE m.from = :user ORDER BY m.sendDate DESC"),
		@NamedQuery(name = "getMessageByIDCount", query = "SELECT COUNT (m) FROM Message m WHERE m.messageId = :id"),
		@NamedQuery(name = "getMessageByID", query = "FROM Message m LEFT JOIN FETCH m.attachements where m.messageId = :id") })
public class Message implements Serializable {

	private static final long serialVersionUID = -7024656440813185483L;

	private Long messageId;

	private String title;

	private String content;

	private User from;

	private User to;

	private Date sendDate;

	private List<Attachement> attachements;

	@Id
	@GeneratedValue(generator = "MessageSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "message_id")
	public Long getMessageId() {
		return messageId;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	@ManyToOne
	public User getFrom() {
		return from;
	}

	@ManyToOne
	public User getTo() {
		return to;
	}

	@Column(name = "sent_date")
	public Date getSendDate() {
		return sendDate;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "message_id")
	public List<Attachement> getAttachements() {
		return attachements;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public void setAttachements(List<Attachement> attachements) {
		this.attachements = attachements;
	}

	/**
	 * Ustawia warto�� pola title
	 * 
	 * @param title
	 *            Nowa warto�� pola title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Zwraca warto�� pola title
	 * 
	 * @return Warto�� pola title
	 */
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
}
