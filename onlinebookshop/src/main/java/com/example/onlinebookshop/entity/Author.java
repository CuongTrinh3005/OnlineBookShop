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
@Table(name="authors", schema="public")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="author_id")
	private Integer authorId;
	@Column(name="author_name")
	private String authorName;
	@Column(name="address")
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany(mappedBy="author", fetch=FetchType.EAGER)
	private Collection<Book> books;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(Integer authorId, String authorName, String address, String phoneNumber, Collection<Book> books) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.books = books;
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

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
}