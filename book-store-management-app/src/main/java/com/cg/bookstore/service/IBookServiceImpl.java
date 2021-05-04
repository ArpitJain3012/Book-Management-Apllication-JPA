package com.cg.bookstore.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IBookRepositoryImpl;

public class IBookServiceImpl implements IBookService {

	private IBookRepository ibr;

	public IBookServiceImpl() {
		ibr = new IBookRepositoryImpl();
	}
	/*
	 * validation for all data inside books table
	 */

	public boolean isValidBookId(int bookId) {
		return bookId != 0 && bookId > 0;
	}

	public boolean isValidTitle(String title) {
		return title != null && (title.length() >= 3 || title.length() <= 20);
	}

	public boolean isValidAuthor(String author) {
		return author != null && (author.length() >= 3 || author.length() <= 20);
	}

	public boolean isValidDescription(String description) {
		return description != null && (description.length() >= 3 || description.length() <= 30);
	}

	public boolean isValidIsbn(String isbn) {
		return isbn != null && (isbn.length() <= 13);
	}

	public boolean isValidPrice(BigInteger price) {
		return price != null;
	}

	public boolean isValidPubDate(LocalDate pubDate) {
		return pubDate != null;
	}

	public boolean isValidLastUpDate(LocalDate lastUpDate) {
		return lastUpDate != null;
	}

	public boolean isValidBook(Book b) throws BMSException {
		List<String> errorMessages = new ArrayList<>();
		boolean isValid = true;

		if (b != null) {
			if (!isValidBookId(b.getBookId())) {
				isValid = false;
				errorMessages.add("book id cannot be blank and must be a positive number");
			}

			if (!isValidTitle(b.getTitle())) {
				isValid = false;
				errorMessages.add("title cannot be blank and must be 3 to 20 characters long");
			}
			if (!isValidAuthor(b.getAuthor())) {
				isValid = false;
				errorMessages.add("author cannot be blank and must be 3 to 20 characters long");
			}
			if (!isValidDescription(b.getDescription())) {
				isValid = false;
				errorMessages.add("description cannot be blank and must be 3 to 30 characters long");
			}
			if (!isValidIsbn(b.getIsbn())) {
				isValid = false;
				errorMessages.add("isbn cannot be blank and must be 13 characters long");
			}
			if (!isValidPrice(b.getPrice())) {
				isValid = false;
				errorMessages.add("price cannot be blank and must be 2 to 10 characters long");
			}
			if (!isValidPubDate(b.getPubDate())) {
				isValid = false;
				errorMessages.add("pubDate cannot be blank and must be a valid one");
			}
			if (!isValidLastUpDate(b.getLastUpDate())) {
				isValid = false;
				errorMessages.add("lastUpDate cannot be blank and must be a valid one");
			}
			if (!errorMessages.isEmpty()) {
				throw new BMSException("Invalid details :" + errorMessages);
			}
		} else {
			isValid = false;
			throw new BMSException("Book details are not supplied");
		}
		return isValid;
	}
	/*
	 * calling repository methods
	 */

	@Override
	public Book createBook(Book b) throws BMSException {
		if (isValidBook(b))
			ibr.createBook(b);
		return b;
	}

	@Override
	public List<Book> listAllBooks() throws BMSException {
		return ibr.listAllBooks();
	}

	@Override
	public Book deleteBook(Book b) throws BMSException {
		return deleteBook(b);
	}

	@Override
	public Book editBook(Book b) throws BMSException {
		return ibr.editBook(b);
	}

	@Override
	public Book viewBook(Book b) throws BMSException {
		return ibr.viewBook(b);
	}

	@Override
	public List<Book> listBooksByCategory(String cat) throws BMSException {

		return ibr.listBooksByCategory(cat);
	}
}