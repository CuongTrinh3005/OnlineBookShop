package com.example.onlinebookshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderDetails", schema="public")
public class OrderDetail {
	@EmbeddedId
	private OrderDetailID orderDetailID;
	
	@Column(name="quantity_order")
	private Integer quantityOrder;
	@Column(name="discount")
	private Float discount;
	@Column(name="unit_price")
	private Float unitPrice;
	
	@Embeddable
	public static class OrderDetailID implements Serializable{
		private static final long serialVersionUID = 1L;
		@Column(name = "order_id")
		private Long orderId;
		@Column(name = "book_id")
		private Long bookId ;
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
	}
	
	@ManyToOne
	@JoinColumn(name="order_id", insertable = false, updatable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="book_id", insertable = false, updatable = false)
	private Book book;
	
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(OrderDetailID orderDetailID, Integer quantityOrder, Float discount, Float unitPrice, Order order,
			Book book) {
		super();
		this.orderDetailID = orderDetailID;
		this.quantityOrder = quantityOrder;
		this.discount = discount;
		this.unitPrice = unitPrice;
		this.order = order;
		this.book = book;
	}

	public OrderDetailID getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(OrderDetailID orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public Integer getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(Integer quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}