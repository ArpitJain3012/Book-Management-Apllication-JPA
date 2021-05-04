package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrders;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.model.OrderDetails;

public interface IOrderService {
	public List<BookOrders> listAllOrders()throws BMSException;
	public List<BookOrders> listOrderByCustomer(Customer cs)throws BMSException;
	public BookOrders viewOrderForCustomer(Customer c)throws BMSException;
	public BookOrders viewOrderForAdmin(BookOrders od)throws BMSException;
	public boolean cancelOrder(BookOrders od)throws BMSException;
	public BookOrders addOrder(BookOrders od)throws BMSException;
	public BookOrders updateOrder(BookOrders od)throws BMSException;
	public List<BookOrders> viewOrderByBook(Book book)throws BMSException;
	public List<Book> listBestSellingBook()throws BMSException;
	}
