package com.cg.bookstore.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.bookstore.model.OrderDetails;

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bookId")
	private int bookId;

	@Column(name = "title", length = 30)
	private String title;

	@Column(name = "author", length = 30)
	private String author;

	@Column(name = "description", length = 30)
	private String description;

	@Column(name = "isbn", length = 30)
	private String isbn;

	@Column(name = "price")
	private BigInteger price;

	@Column(name = "pubDate", length = 30)
	private LocalDate pubDate;

	@Column(name = "lastUpDate", length = 30)
	private LocalDate lastUpDate;
	/*
	 * using many to one association for mapping with category entity
	 */
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	/*
	 * using one to many association for mapping with review entity
	 */
	@OneToMany(mappedBy = "book1", cascade = CascadeType.PERSIST)
	private Set<Review> rev;

	/*
	 * embedding order details
	 */
	@Embedded
	private OrderDetails od;

	public Book() {
		// no implementation
	}

	public Book(int bookId, String title, String author, String description, String isbn, BigInteger price,
			LocalDate pubDate, LocalDate lastUpDate, OrderDetails od) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.pubDate = pubDate;
		this.lastUpDate = lastUpDate;
		this.od = od;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public LocalDate getPubDate() {
		return pubDate;
	}

	public void setPubDate(LocalDate pubDate) {
		this.pubDate = pubDate;
	}

	public LocalDate getLastUpDate() {
		return lastUpDate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpDate = lastUpdate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Review> getRev() {
		return rev;
	}

	public void setRev(Set<Review> rev) {
		this.rev = rev;
	}

	public OrderDetails getOd() {
		return od;
	}

	public void setOd(OrderDetails od) {
		this.od = od;
	}

	@Override
	public String toString() {
		return String.format("bookId=%s,title=%s,author=%s,description=%s,isbn=%s,price=%s,pubDate=%s,lastUpDate=%s",
				bookId, title, author, description, isbn, price, pubDate, lastUpDate);
	}
}