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
import com.cg.bookstore.entities.BookOrders;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.model.OrderDetails;

public class ICustomerRepositoryImpl implements ICustomerRepository {
	public final EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
	public final EntityManager em = emf.createEntityManager();
	public final EntityTransaction txn = em.getTransaction();

	/*
	 * to add customers in database
	 */
	@Override
	public Customer createCustomer(Customer c) throws BMSException {
		EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(c);
		txn.commit();
		return c;
	}
	/*
	 * to retrieve list of customers in database
	 */

	@Override
	public List<Customer> listCustomers() throws BMSException {

		String jpql = "SELECT e FROM Customer e";
		TypedQuery<Customer> tqry = em.createQuery(jpql, Customer.class);
		List<Customer> list = tqry.getResultList();
		return list;
	}
	/*
	 * to delete customers from database
	 */

	@Override
	public boolean deleteCustomer(Customer c) throws BMSException {
		int id = c.getCustomerId();
		c = em.find(Customer.class, id);
		txn.begin();
		em.remove(c);
		txn.commit();
		System.out.println("Customer data deleted successfully...");
		return true;
	}

	/*
	 * to update customers in databse
	 */
	@Override
	public Customer updateCustomer(Customer c) throws BMSException {
		Scanner sc = new Scanner(System.in);
		int id = c.getCustomerId();
		c = em.find(Customer.class, id);

		EntityTransaction txn = em.getTransaction();

		System.out.println("Enter customer name");
		String name = sc.nextLine();
		c.setFullName(name);

		System.out.println("Enter customer email");
		String e = sc.next();
		c.setEmail(e);

		System.out.println("Enter password");
		String p = sc.next();
		c.setPassword(p);

		System.out.println("Enter mobile number");
		String mob = sc.next();
		c.setMobileNumber(mob);

		System.out.println("Enter new registerd date(yyyy-MM-dd):");
		LocalDate modifiedDate = LocalDate.parse(sc.next());
		c.setRegisterOn(modifiedDate);

		txn.begin();
		em.merge(c);
		txn.commit();
		System.out.println("Customer with #" + id + "is modified");
		return c;
	}
	/*
	 * to view particular customer
	 */

	@Override
	public Customer viewCustomer(Customer c) throws BMSException {
		int id = c.getCustomerId();
		c = em.find(Customer.class, id);
		return c;
	}
	/*
	 * to view customers having particular book
	 */

	@Override
	public List<Customer> listAllCustomersByBook(Book book) throws BMSException {
		List<Customer> lis = null;
		int id = book.getBookId();
		try {
			String jpql = "SELECT e FROM Customer e WHERE EXISTS (SELECT bo FROM BookOrder bo WHERE EXISTS(SELECT b FROM Books b WHERE b.bookI?1))";
			TypedQuery<Customer> tqry = em.createQuery(jpql, Customer.class);
			tqry.setParameter(1, id);
			lis = tqry.getResultList();

		} catch (Exception e) {
			throw new BMSException("Customer through book not found");
		}
		return lis;
	}
}
