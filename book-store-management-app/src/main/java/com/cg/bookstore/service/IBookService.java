package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;

public interface IBookService {

	public Book createBook(Book b)throws BMSException;
	public List<Book> listAllBooks()throws BMSException;
	public Book deleteBook(Book b)throws BMSException;
	public Book editBook(Book b)throws BMSException;
	public Book viewBook(Book b)throws BMSException;
	public List<Book> listBooksByCategory(String cat)throws BMSException;

}
