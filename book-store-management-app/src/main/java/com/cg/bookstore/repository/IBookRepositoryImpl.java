package com.cg.bookstore.repository;

import java.math.BigInteger;
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
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;

public class IBookRepositoryImpl implements IBookRepository {
	public final EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
	public final EntityManager em = emf.createEntityManager();
	public final EntityTransaction txn = em.getTransaction();

	Book book;

	/*
	 * to add books in database
	 */

	@Override
	public Book createBook(Book b) throws BMSException {
		EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(b);
		txn.commit();
		return b;
	}
	/*
	 * to retrive list of all books from database
	 */

	@Override
	public List<Book> listAllBooks() throws BMSException {
		String jpql = "SELECT b FROM Books b";
		TypedQuery<Book> tqry = em.createQuery(jpql, Book.class);
		List<Book> list = tqry.getResultList();
		return list;
	}

	/*
	 * to delete books data from database
	 */
	@Override
	public boolean deleteBook(Book b) throws BMSException {
		int id = b.getBookId();
		b = em.find(Book.class, id);
		txn.begin();
		em.remove(b);
		txn.commit();
		System.out.println("Book data deleted successfully...");
		return true;
	}

	/*
	 * to update book data in database
	 */

	@Override
	public Book editBook(Book b) throws BMSException {
		Scanner sc = new Scanner(System.in);
		int id = b.getBookId();
		b = em.find(Book.class, id);

		EntityTransaction txn = em.getTransaction();

		System.out.println("Enter Book Title");
		String title = sc.nextLine();
		b.setTitle(title);

		System.out.println("Enter Book Author");
		String auth = sc.next();
		b.setAuthor(auth);

		System.out.println("Enter Description");
		String desc = sc.next();
		b.setDescription(desc);

		System.out.println("Enter ISBN");
		String i = sc.next();
		b.setIsbn(i);

		System.out.println("Enter Price");
		BigInteger p = sc.nextBigInteger();
		b.setPrice(p);

		System.out.println("Enter Published date(yyyy-MM-dd):");
		LocalDate pDate = LocalDate.parse(sc.next());
		b.setPubDate(pDate);

		System.out.println("Enter last updated date(yyyy-MM-dd):");
		LocalDate lDate = LocalDate.parse(sc.next());
		b.setLastUpdate(lDate);

		txn.begin();
		em.merge(b);
		txn.commit();
		System.out.println("Book with #" + id + "is modified");
		return b;
	}

	/*
	 * to view book of particular id
	 */

	@Override
	public Book viewBook(Book b) throws BMSException {
		int id = b.getBookId();
		b = em.find(Book.class, id);
		return b;
	}

	/*
	 * list of books of particular categories
	 */
	@Override
	public List<Book> listBooksByCategory(String cat) throws BMSException {
		List<Book> list = new ArrayList<>();
		try {
			String jpql = "SELECT b FROM book b WHERE EXISTS(SELECT c FROM Category WHERE c.categoryName=?1)";
			TypedQuery<Book> tqry = em.createQuery(jpql, Book.class);
			tqry.setParameter(1, cat);
			list = tqry.getResultList();
		} catch (Exception e) {
			throw new BMSException("Books by category name not found");
		}
		return list;
	}

}
