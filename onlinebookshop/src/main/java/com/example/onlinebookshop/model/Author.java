package com.example.onlinebookshop.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="authors", schema="public")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="author_id")
	private Integer authorId;
	@Column(name="author_name")
	@NotBlank
	private String authorName;
	@Column(name="address")
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}
	
	public Author(Integer authorId, String authorName, String address, String phoneNumber, Set<Book> books) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
}