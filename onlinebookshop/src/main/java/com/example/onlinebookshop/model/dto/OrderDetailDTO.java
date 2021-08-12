package com.example.onlinebookshop.model.dto;

public class OrderDetailDTO {
	private Long orderId;
	private Long bookId;
	private String bookName;
	private byte[] photo;
	private Integer orderQuantity;
	private Float unitPrice;
	private Float discount;
	
	public OrderDetailDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetailDTO(Long orderId, Long bookId, String bookName, byte[] photo, Integer orderQuantity,
			Float unitPrice, Float discount) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.photo = photo;
		this.orderQuantity = orderQuantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
	}

	public OrderDetailDTO(Long orderId, Long bookId, Integer orderQuantity,
			Float unitPrice, Float discount) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.orderQuantity = orderQuantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	
}
