package com.example.onlinebookshop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories", schema="public")
public class Category {
	@Id
	@Column(name="category_id")
	private String categoryId;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="description")
	private String description;

	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private Collection<Book> books;
	
	public Category() {
		
	}

	public Category(String categoryId, String categoryName, String description, Collection<Book> books) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		this.books = books;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return categoryName;
	}

	public void setName(String name) {
		this.categoryName = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}