package com.cg.bookstore.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "categoryId")
	private int categoryId;

	@Column(name = "categoryName", length = 20)
	private String categoryName;
	/*
	 * using one to many association for mapping with book entity
	 */
	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
	private Set<Book> book;

	public Category() {
		// no implementation

	}

	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return String.format("categoryId=%s, categoryName=%s", categoryId, categoryName);

	}
}