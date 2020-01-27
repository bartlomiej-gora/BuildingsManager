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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Klasa odzwierciedlaj�ca tabel� z za�acznikami do wiadomo�ci.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "Attachement")
@SequenceGenerator(name = "AttachementSequence", sequenceName = "AttachementSequence", initialValue = 0, allocationSize = 1)
public class Attachement implements Serializable {

	private static final long serialVersionUID = -5021192548502135093L;

	private Long attachementId;

	private String filename;

	private byte[] content;

	private Message message;

	private String format;

	private Document documentType;

	@Id
	@GeneratedValue(generator = "AttachementSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "atta_id")
	public Long getAttachementId() {
		return attachementId;
	}

	@Column(name = "filename")
	public String getFilename() {
		return filename;
	}

	@Column(name = "content")
	public byte[] getContent() {
		return content.clone();
	}

	@ManyToOne
	@JoinColumn(name = "message_id")
	public Message getMessage() {
		return message;
	}

	@Column(name = "format")
	public String getFormat() {
		return format;
	}

	@ManyToOne
	@JoinColumn(name = "document_id")
	public Document getDocumentType() {
		return documentType;
	}

	public void setAttachementId(Long attachementId) {
		this.attachementId = attachementId;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setContent(byte[] content) {
		this.content = content.clone();
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setDocumentType(Document documentType) {
		this.documentType = documentType;
	}

	/**
	 * Nadpisanie metody z klasy bazowej
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Attachement) {
			Attachement other = (Attachement) obj;
			if (other == this
					|| (other.getFilename() != null && other.getFilename()
							.equals(getFilename()))) {
				return true;
			}
		}
		return false;
	}
}
