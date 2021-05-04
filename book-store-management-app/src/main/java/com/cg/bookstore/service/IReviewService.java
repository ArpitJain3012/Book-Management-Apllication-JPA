package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;

public interface IReviewService {
	public List<Review> listAllReviews()throws BMSException;
	public Review addReview(Review review )throws BMSException;
	public Review updateReview(Review review )throws BMSException;
	public Review deleteReview(Review review )throws BMSException;
	public Review viewReview(Review review )throws BMSException;
	public List<Review> listAllReviewsByBook(Book book)throws BMSException;
	public List<Review> listAllReviewsByCustomer(Customer customer)throws BMSException;
	public List<Book> listMostFavoredBooks()throws BMSException;

}
