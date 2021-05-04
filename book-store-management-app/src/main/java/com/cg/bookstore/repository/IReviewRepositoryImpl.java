package com.cg.bookstore.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;

public class IReviewRepositoryImpl implements IReviewRepository {
	public final EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
	public final EntityManager em = emf.createEntityManager();
	public final EntityTransaction txn = em.getTransaction();

	/*
	 * list of all reviews
	 */
	@Override
	public List<Review> listAllReviews() throws BMSException {
		String jpql = "SELECT r FROM Review r";
		TypedQuery<Review> tqry = em.createQuery(jpql, Review.class);
		List<Review> list = tqry.getResultList();
		return list;
	}

	/*
	 * adding review in database
	 */
	@Override
	public Review addReview(Review review) throws BMSException {
		int id = review.getReviewId();
		review = em.find(Review.class, id);
		txn.begin();
		em.persist(review);
		txn.commit();
		System.out.println("Review data added successfully...");
		return review;
	}

	/*
	 * delete review from database
	 */
	@Override
	public boolean deleteReview(Review review) throws BMSException {
		int id = review.getReviewId();
		review = em.find(Review.class, id);
		txn.begin();
		em.remove(review);
		txn.commit();
		System.out.println("Review data deleted successfully...");
		return true;
	}

	/*
	 * update review in database
	 */
	@Override
	public Review updateReview(Review review) throws BMSException {
		Scanner sc = new Scanner(System.in);
		int id = review.getReviewId();
		review = em.find(Review.class, id);

		EntityTransaction txn = em.getTransaction();

		System.out.println("Enter headline name");
		String hl = sc.nextLine();
		review.setHeadline(hl);

		System.out.println("Enter comment");
		String com = sc.next();
		review.setComment(com);

		System.out.println("Enter rating");
		String rate = sc.next();
		review.setRating(rate);

		System.out.println("Enter new review date(yyyy-MM-dd):");
		LocalDate reviewDate = LocalDate.parse(sc.next());
		review.setReviewon(reviewDate);

		txn.begin();
		em.merge(review);
		txn.commit();
		System.out.println("Review with #" + id + "is modified");
		return review;
	}

	/*
	 * view particular review
	 */
	@Override
	public Review viewReview(Review review) throws BMSException {
		int id = review.getReviewId();
		review = em.find(Review.class, id);
		return review;
	}

	/*
	 * list of review by books
	 */
	@Override
	public List<Review> listAllReviewsByBook(Book book) throws BMSException {
		List<Review> list = new ArrayList<>();
		int id = book.getBookId();
		try {
			String jpql = "SELECT r FROM Review r WHERE (SELECT b FROM Books b WHERE b.bookId=?1)";
			TypedQuery<Review> tqry = em.createQuery(jpql, Review.class);
			tqry.setParameter(1, id);
			list = tqry.getResultList();

		} catch (Exception e) {
			throw new BMSException("Review by book id not found");
		}
		return list;
	}

	/*
	 * list of review by customer
	 */
	@Override
	public List<Review> listAllReviewsByCustomer(Customer c) throws BMSException {
		int id = c.getCustomerId();
		List<Review> list = new ArrayList<>();
		try {
			String jpql = "SELECT r FROM Review r WHERE (SELECT c FROM Customer c WHERE c.customerId=?1)";
			TypedQuery<Review> tqry = em.createQuery(jpql, Review.class);
			tqry.setParameter(1, id);
			list = tqry.getResultList();

		} catch (Exception e) {
			throw new BMSException("Review by customer id not found");
		}
		return list;
	}

	/*
	 * list of most favored books
	 */
	@Override
	public List<Book> listMostFavoredBooks() throws BMSException {
		String jpql = "SELECT b FROM Books b WHERE EXISTS(SELECT r FROM Review r WHERE r.rating='5 star' OR r.rating='4 star)";
		TypedQuery<Book> tqry = em.createQuery(jpql, Book.class);
		List<Book> list = tqry.getResultList();
		return list;
	}

}
