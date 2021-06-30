package com.example.onlinebookshop.entity;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ratings", schema="public")
public class Ratings {
	@EmbeddedId
	private RatingId ratingId;
	
	@Column(name="date_rating")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateRating;
	@Column(name="level_rating")
	private Integer levelRating;
	
	@Embeddable
	public static class RatingId implements Serializable{
		private static final long serialVersionUID = 1L;
		@Column(name = "customer_id")
		private String customerId;
		@Column(name = "book_id")
		private String bookId ;
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getBookId() {
			return bookId;
		}
		public void setBookId(String bookId) {
			this.bookId = bookId;
		}
	}
	
	@ManyToOne
	@JoinColumn(name="book_id", insertable = false, updatable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="customer_id", insertable = false, updatable = false)
	private Customer customer;
	
	public Ratings() {
		// TODO Auto-generated constructor stub
	}

	public Ratings(RatingId ratingId, Date dateRating, Integer levelRating, Book book, Customer customer) {
		super();
		this.ratingId = ratingId;
		this.dateRating = dateRating;
		this.levelRating = levelRating;
		this.book = book;
		this.customer = customer;
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

	public Integer getLevelRating() {
		return levelRating;
	}

	public void setLevelRating(Integer levelRating) {
		this.levelRating = levelRating;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
