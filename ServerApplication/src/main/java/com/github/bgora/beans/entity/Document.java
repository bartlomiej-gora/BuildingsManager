/**
 * 
 */
package com.github.bgora.beans.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
 * Klasa odzworowuj�ca dokument.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@NamedQueries(value = { @NamedQuery(name = "getDocuments", query = " FROM Document ORDER BY title") })
@Table(name = "Document")
@SequenceGenerator(name = "DocumentSequence", sequenceName = "DocumentSequence", initialValue = 0, allocationSize = 1)
public class Document implements Serializable {

	private static final long serialVersionUID = -783541931759096042L;

	private Long documentId;

	private Date createDate;

	private Date expirationDate;

	private User author;

	private String title;

	private String type;

	private Double version;

	private Byte[] content;

	private List<Attachement> attachements;

	@Id
	@GeneratedValue(generator = "DocumentSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "document_id")
	public Long getDocumentId() {
		return documentId;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name = "expire_date")
	public Date getExpirationDate() {
		return expirationDate;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getAuthor() {
		return author;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	@Column(name = "version")
	public Double getVersion() {
		return version;
	}

	@Column(name = "content")
	public Byte[] getContent() {
		return content;
	}

	@OneToMany(mappedBy = "documentType")
	public List<Attachement> getAttachements() {
		return attachements;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

	public void setContent(Byte[] content) {
		this.content = content.clone();
	}

	public void setAttachements(List<Attachement> attachements) {
		this.attachements = attachements;
	}
}
