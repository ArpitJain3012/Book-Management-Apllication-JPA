package com.cg.bookstore.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")

public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "reviewId")
	private int reviewId;

	@Column(name = "headline", length = 30)
	private String headline;

	@Column(name = "comments", length = 20)
	private String comment;

	@Column(name = "rating", length = 20)
	private String rating;

	@Column(name = "reviewOn")
	private LocalDate reviewOn;

	/*
	 * using many to one association for mapping with customer entity
	 */

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer cust1;

	/*
	 * using many to one association for mapping with book entity
	 */

	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book1;

	public Review() {
		// no implementation
	}

	public Review(int reviewId, String headline, String comment, String rating, LocalDate reviewOn) {
		super();
		this.reviewId = reviewId;
		this.headline = headline;
		this.comment = comment;
		this.rating = rating;
		this.reviewOn = reviewOn;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public LocalDate getReviewon() {
		return reviewOn;
	}

	public void setReviewon(LocalDate reviewOn) {
		this.reviewOn = reviewOn;
	}

	public Customer getcust1() {
		return cust1;
	}

	public void setcust1(Customer cust1) {
		this.cust1 = cust1;
	}

	public Book getbook1() {
		return book1;
	}

	public void setAddress1(Book book1) {
		this.book1 = book1;
	}

	@Override
	public String toString() {
		return String.format("reviewId=%s, headline=%s, comment=%s, rating=%s,reviewOn=%s ", reviewId, headline,
				comment, rating, reviewOn);
	}

}
