package com.example.onlinebookshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ratings", schema="public")
public class Rating {
	@EmbeddedId
	private RatingId ratingId;
	
	@Column(name="date_rating")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateRating;
	@Column(name="level_rating")
	@DecimalMin(value="0")
	@DecimalMax(value="5")
	private Float levelRating;
	@Column(name="comment")
	private String comment;
	
	@Embeddable
	public static class RatingId implements Serializable{
		private static final long serialVersionUID = 1L;
		@Column(name = "username")
		@NotBlank
		private String username;
		@Column(name = "book_id")
		@NotNull
		private Long bookId ;
		
		public RatingId() {
			super();
		}
		
		public RatingId(String username, Long bookId) {
			this.username = username;
			this.bookId = bookId;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Long getBookId() {
			return bookId;
		}
		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}
	}
	
	@ManyToOne
	@JoinColumn(name="book_id", insertable = false, updatable = false)
	@JsonIgnore
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="username", insertable = false, updatable = false)
	@JsonIgnore
	private User user;
	
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(RatingId ratingId, Date dateRating, Float levelRating, String comment, Book book, User user) {
		super();
		this.ratingId = ratingId;
		this.dateRating = dateRating;
		this.levelRating = levelRating;
		this.comment = comment;
		this.book = book;
		this.user = user;
	}

	public RatingId getRatingId() {
		return ratingId;
	}

	public void setRatingId(RatingId ratingId) {
		this.ratingId = ratingId;
	}

	public Date getDateRating() {
		return dateRating;
	}

	public void setDateRating(Date dateRating) {
		this.dateRating = dateRating;
	}

	public Float getLevelRating() {
		return levelRating;
	}

	public void setLevelRating(Float levelRating) {
		this.levelRating = levelRating;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}