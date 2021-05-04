package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;

public interface ICustomerService {

	public Customer createCustomer(Customer c)throws BMSException;
	public List<Customer> listCustomers()throws BMSException;
	public Customer deleteCustomer(Customer c)throws BMSException;
	public Customer updateCustomer(Customer c)throws BMSException;
	public Customer viewCustomer(Customer c)throws BMSException;
	public List<Customer> listAllCustomersByBook(Book book)throws BMSException;


}
