package com.cg.bookstore.repository;

import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;

public interface IBookRepository {
	public Book createBook(Book b)throws BMSException ;
	public List<Book> listAllBooks()throws BMSException ;
	public boolean deleteBook(Book b)throws BMSException ;
	public Book editBook(Book b)throws BMSException ;
	public Book viewBook(Book b)throws BMSException ;
	public List<Book> listBooksByCategory(String cat)throws BMSException ;

}
