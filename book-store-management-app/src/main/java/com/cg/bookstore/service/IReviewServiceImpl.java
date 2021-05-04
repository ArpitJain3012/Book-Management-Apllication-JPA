package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.repository.IReviewRepository;
import com.cg.bookstore.repository.IReviewRepositoryImpl;

public class IReviewServiceImpl implements IReviewService {
	private IReviewRepository irs;

	public IReviewServiceImpl() {
		irs = new IReviewRepositoryImpl();
	}
	/*
	 * validation for all reviews
	 */

	public boolean isValidreviewId(int reviewId) {
		return reviewId != 0 && reviewId > 0;
	}

	public boolean isValidheadline(String headline) {
		return headline != null && (headline.length() >= 3 || headline.length() <= 20);
	}

	public boolean isValidcomment(String comment) {
		return comment != null && (comment.length() >= 3 || comment.length() <= 20);
	}

	public boolean isValidrating(String rating) {
		return rating != null && (rating.length() >= 5 || rating.length() <= 7);
	}

	public boolean isValidreviewOn(LocalDate reviewOn) {
		return reviewOn != null;
	}

	public boolean isValidReview(Review rs) throws BMSException {
		List<String> errorMessages = new ArrayList<>();
		boolean isValid = true;

		if (rs != null) {
			if (!isValidreviewId(rs.getReviewId())) {
				isValid = false;
				errorMessages.add("reviewId cannot be blank and must be a posiitive number");
			}
			if (!isValidheadline(rs.getHeadline())) {
				isValid = false;
				errorMessages.add("headline cannot be blank and must be 3 to 20 characters long");
			}
			if (!isValidcomment(rs.getComment())) {
				isValid = false;
				errorMessages.add("comment cannot be blank and must be a valid one");
			}
			if (!isValidrating(rs.getRating())) {
				isValid = false;
				errorMessages.add("rating cannot be blank ");
			}
			if (!isValidreviewOn(rs.getReviewon())) {
				isValid = false;
				errorMessages.add("reviewOn cannot be blank and must be a valid one");
			}
			if (!errorMessages.isEmpty()) {
				throw new BMSException("Invalid review :" + errorMessages);
			}
		} else {
			isValid = false;
			throw new BMSException("ReviewService details are not supplied");
		}
		return isValid;
	}

	/*
	 * calling repository methods
	 */

	@Override
	public List<Review> listAllReviews() throws BMSException {
		return irs.listAllReviews();
	}

	@Override
	public Review addReview(Review review) throws BMSException {
		if (isValidReview(review))
			irs.addReview(review);
		return review;
	}

	@Override
	public Review updateReview(Review review) throws BMSException {
		return irs.updateReview(review);
	}

	@Override
	public Review deleteReview(Review review) throws BMSException {
		irs.deleteReview(review);
		return review;
	}

	@Override
	public Review viewReview(Review review) throws BMSException {
		return irs.viewReview(review);
	}

	@Override
	public List<Review> listAllReviewsByBook(Book book) throws BMSException {
		return irs.listAllReviewsByBook(book);
	}

	@Override
	public List<Review> listAllReviewsByCustomer(Customer customer) throws BMSException {
		return irs.listAllReviewsByCustomer(customer);
	}

	@Override
	public List<Book> listMostFavoredBooks() throws BMSException {
		return irs.listMostFavoredBooks();
	}

}
