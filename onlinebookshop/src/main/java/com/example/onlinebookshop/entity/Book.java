package com.example.onlinebookshop.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="books", schema="public")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private Long bookId;
	@Column(name="book_name")
	private String bookName;
	@Column(name="unit_price")
	private Float unitPrice;
	@Column(name="quantity")
	private Long quantity;
	@Column(name="discount")
	private Float discount;
//	private String categoryId;
	@Column(name="photo")
	private byte[] photo;
	@Column(name="description")
	private String description;
	@Column(name="specification")
	private String specification;
	@Column(name="view_count")
	private Long viewCount;
	@Column(name="special")
	private Boolean special;
	@Column(name="available")
	private Boolean available;
	@Column(name="date_in")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateIn;
	@Column(name="date_update")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateUpdate;
//	private Integer authorId;
//	private Integer publisherId;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonIgnore
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	@JsonIgnore
	private Author author;
	
	@ManyToOne
	@JoinColumn(name="publisher_id")
	@JsonIgnore
	private Publisher publisher;
	
	@OneToMany(mappedBy="book", fetch=FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy="book", fetch=FetchType.EAGER)
	private Collection<Ratings> ratings;
	
	public Book() {
		
	}

	public Book(Long bookId, String bookName, Float unitPrice, Long quantity, Float discount, byte[] photo,
			String description, String specification, Long viewCount, Boolean special, Boolean available, Date dateIn,
			Date dateUpdate, Category category, Author author, Publisher publisher,
			Collection<OrderDetail> orderDetails, Collection<Ratings> ratings) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
		this.photo = photo;
		this.description = description;
		this.specification = specification;
		this.viewCount = viewCount;
		this.special = special;
		this.available = available;
		this.dateIn = dateIn;
		this.dateUpdate = dateUpdate;
		this.category = category;
		this.author = author;
		this.publisher = publisher;
		this.orderDetails = orderDetails;
		this.ratings = ratings;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Boolean getSpecial() {
		return special;
	}

	public void setSpecial(Boolean special) {
		this.special = special;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Collection<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Ratings> ratings) {
		this.ratings = ratings;
	}
}