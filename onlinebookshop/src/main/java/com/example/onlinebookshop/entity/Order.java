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

@Entity
@Table(name="orders", schema="public")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long orderId;
	@Column(name="order_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date orderDate;
//	private String customerId;
	@Column(name="order_address")
	private String orderAddress;
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Collection<OrderDetail> orderDetail;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Long orderId, Date orderDate, String orderAddress, String description,
			Collection<OrderDetail> orderDetail, Customer customer) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderAddress = orderAddress;
		this.description = description;
		this.orderDetail = orderDetail;
		this.customer = customer;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Collection<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}