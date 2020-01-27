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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Klasa odzworowuj�ca Wiadomo�ci na stronie g��wnej. Odzworowuje tebel�
 * wiadomo�ci w bazie danych.
 * 
 * @author Bart�omiej G�ra
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Entity
@Table(name = "News")
@NamedQueries(value = { @NamedQuery(name = "getRecentNews", query = "FROM News n LEFT JOIN FETCH n.user ORDER BY n.newsDate DESC") })
@SequenceGenerator(name = "NewsSequence", sequenceName = "NewsSequence", initialValue = 0, allocationSize = 1)
public class News implements Serializable {

	private static final long serialVersionUID = -922660876873517093L;

	private Long newsId;

	private Date newsDate;

	private String title;

	private String content;

	private User user;

	@Id
	@GeneratedValue(generator = "NewsSequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "news_id")
	public Long getNewsId() {
		return newsId;
	}

	@Column(name = "news_date")
	public Date getNewsDate() {
		return newsDate;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
