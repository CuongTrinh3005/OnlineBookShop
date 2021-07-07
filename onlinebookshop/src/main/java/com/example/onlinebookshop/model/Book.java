package com.example.onlinebookshop.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books", schema = "public")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	@Column(name = "book_name")
	@NotBlank
	private String bookName;
	@Column(name = "unit_price")
	@DecimalMin(value = "0", message = "Price must be not under 0")
	private Float unitPrice;
	@Column(name = "quantity")
	@DecimalMin(value = "0", message = "Quantity must be not under 0")
	private Long quantity;
	@Column(name = "discount")
	@DecimalMin(value = "0", message = "Discount must be not under 0%")
	@DecimalMax(value = "0.7", message = "Discount must be not over 70%")
	private Float discount;
	@Column(name = "photo")
	private byte[] photo;
	@Column(name = "description")
	private String description;
	@Column(name = "specification")
	private String specification;
	@Column(name = "view_count")
	@DecimalMin(value = "0", message = "No. view must be not under 0")
	private Long viewCount;
	@Column(name = "special")
	private Boolean special;
	@Column(name = "available")
	private Boolean available;
	@Column(name = "date_in")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "Not allow to choose day in future")
	private Date dateIn;
	@Column(name = "date_update")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "Not allow to choose day in future")
	private Date dateUpdate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	// @JsonIgnore
	private Publisher publisher;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "written", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private Collection<Ratings> ratings;

	public Book() {

	}

	public Book(Long bookId, @NotBlank String bookName,
			@DecimalMin(value = "0", message = "Price must be not under 0") Float unitPrice,
			@DecimalMin(value = "0", message = "Quantity must be not under 0") Long quantity,
			@DecimalMin(value = "0", message = "Discount must be not under 0%") @DecimalMax(value = "0.7", message = "Discount must be not over 70%") Float discount,
			byte[] photo, String description, String specification,
			@DecimalMin(value = "0", message = "No. view must be not under 0") Long viewCount, Boolean special,
			Boolean available, @Past(message = "Not allow to choose day in future") Date dateIn,
			@Past(message = "Not allow to choose day in future") Date dateUpdate, Category category,
			Publisher publisher, Set<Author> authors, Collection<OrderDetail> orderDetails,
			Collection<Ratings> ratings) {
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
		this.publisher = publisher;
		this.authors = authors;
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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
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

	public void update(Book book) {
		this.setBookName(book.getBookName());
		this.setDescription(book.getDescription());
		this.setDiscount(book.getDiscount());
		this.setAvailable(book.getAvailable());
		this.setPhoto(book.getPhoto());

		this.setQuantity(book.getQuantity());
		this.setUnitPrice(book.getUnitPrice());
		this.setSpecial(book.getSpecial());
		this.setSpecification(book.getSpecification());
		this.setViewCount(book.getViewCount());
//		this.setDateUpdate(new Date());

		this.setCategory(book.getCategory());
		this.setAuthors(book.getAuthors());

		this.setPublisher(book.getPublisher());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bookId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(bookId, other.bookId);
	}

	@Override
	public String toString() {
		return "Book [" + ", id=" + bookId + ", name=" + bookName + "description: " + description + "]";
	}
}