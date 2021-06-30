package com.example.onlinebookshop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customers", schema="public")
public class Customer {
	@Id
	@Column(name="customer_id")
	private String customerId;
	@Column(name="password")
	private String password;
	@Column(name="customer_name")
	private String customerName;
	@Column(name="gender")
	private Boolean gender;
	@Column(name="address")
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="email")
	private String email;
	@Column(name="photo")
	private byte[] photo;
	@Column(name="admin")
	private Boolean admin;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	private Collection<Order> orders;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	private Collection<Ratings> ratings;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String customerId, String password, String customerName, Boolean gender, String address,
			String phoneNumber, String email, byte[] photo, Boolean admin, Collection<Order> orders,
			Collection<Ratings> ratings) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.customerName = customerName;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.photo = photo;
		this.admin = admin;
		this.orders = orders;
		this.ratings = ratings;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Ratings> ratings) {
		this.ratings = ratings;
	}

	
}
