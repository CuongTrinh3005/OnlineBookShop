package com.example.onlinebookshop.model.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class BookDTO {
	private Long bookId;
	@NotBlank
	private String bookName;
	@DecimalMin(value = "0", message = "Price must be not under 0")
	private Float unitPrice;
	@DecimalMin(value = "0", message = "Quantity must be not under 0")
	private Long quantity;
	@DecimalMin(value = "0", message = "Discount must be not under 0%")
	@DecimalMax(value = "0.7", message = "Discount must be not over 70%")
	private Float discount;
	private byte[] photo;
	private String description;
	private String specification;
	@DecimalMin(value = "0", message = "No. view must be not under 0")
	private Long viewCount;
	private Boolean special;
	private Boolean available;
	@NotBlank
	private String categoryName;
	@NotBlank
	private String publisherName;
	@Size(min = 1)
	private String[] authorIds = new String[0];

	public BookDTO() {

	}

	public BookDTO(Long bookId, String bookName, Float unitPrice, Long quantity, Float discount, byte[] photo,
			String description, String specification, Long viewCount, Boolean special, Boolean available,
			String categoryName, String publisherName, String[] authorIds) {
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
		this.categoryName = categoryName;
		this.publisherName = publisherName;
		this.authorIds = authorIds;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String[] getAuthorIds() {
		return authorIds;
	}

	public void setAuthorIds(String[] authorIds) {
		this.authorIds = authorIds;
	}
}