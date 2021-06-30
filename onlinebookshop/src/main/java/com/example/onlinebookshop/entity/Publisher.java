package com.example.onlinebookshop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="publishers", schema="public")
public class Publisher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="publisher_id")
	private Integer publisherId;
	@Column(name="publisher_name")
	private String publisherName;
	@Column(name="address")
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany(mappedBy="publisher", fetch=FetchType.EAGER)
	private Collection<Book> books;
	
	public Publisher() {
		// TODO Auto-generated constructor stub
	}
	
	public Publisher(Integer publisherId, String publisherName, String address, String phoneNumber,
			Collection<Book> books) {
		super();
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.books = books;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
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

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
}