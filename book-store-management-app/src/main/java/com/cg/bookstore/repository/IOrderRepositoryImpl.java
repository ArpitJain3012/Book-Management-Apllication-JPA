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
import com.cg.bookstore.entities.BookOrders;
import com.cg.bookstore.entities.Customer;

public class IOrderRepositoryImpl implements IOrderRepository {

	public final EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
	public final EntityManager em = emf.createEntityManager();
	public final EntityTransaction txn = em.getTransaction();
	BookOrders bo;

	/*
	 * list of orders
	 */
	@Override
	public List<BookOrders> listAllOrders() throws BMSException {
		String jpql = "SELECT e FROM BookOrder e";
		TypedQuery<BookOrders> tqry = em.createQuery(jpql, BookOrders.class);
		List<BookOrders> ls = tqry.getResultList();
		return ls;
	}

	/*
	 * list of orders by particular customer
	 */
	@Override
	public List<BookOrders> listOrderByCustomer(Customer cs) throws BMSException {
		List<BookOrders> list = new ArrayList<>();
		try {
			int id = cs.getCustomerId();
			String jpql = "SELECT e FROM BookOrders e where EXISTS(SELECT cs FROM Customer cs WHERE cs.customerId=?1) ";
			TypedQuery<BookOrders> tqry = em.createQuery(jpql, BookOrders.class);
			tqry.setParameter(1, id);
			list = tqry.getResultList();

		} catch (Exception e) {
			throw new BMSException("Order list for customers not found");
		}
		return list;
	}
	/*
	 * order of one customer
	 */

	@Override
	public BookOrders viewOrderForCustomer(Customer c) throws BMSException {
		BookOrders bo = new BookOrders();
		if (bo.getCust().equals(c.getCustomerId())) {
			int id = bo.getOrderId();
			bo = em.find(BookOrders.class, id);
		}
		return bo;
	}

	/*
	 * view order detials for admin
	 */
	@Override
	public BookOrders viewOrderForAdmin(BookOrders od) throws BMSException {
		if (od.getOrderId() != 0) {
			int id = od.getOrderId();
			od = em.find(BookOrders.class, id);
		} else {
			System.out.println("No orders for " + od.getOrderId() + " is found");
			return null;
		}
		return od;
	}

	/*
	 * cancel order
	 */
	@Override
	public boolean cancelOrder(BookOrders od) throws BMSException {
		int id = od.getOrderId();
		od = em.find(BookOrders.class, id);
		txn.begin();
		em.remove(od);
		txn.commit();
		System.out.println("Canceled order successfully...");
		return true;
	}

	/*
	 * add order details in database
	 */
	@Override
	public BookOrders addOrder(BookOrders od) throws BMSException {
		txn.begin();
		em.persist(od);
		txn.commit();
		return od;
	}

	/*
	 * update order detials in database
	 */
	@Override
	public BookOrders updateOrder(BookOrders od) throws BMSException {

		Scanner sc = new Scanner(System.in);
		int id = od.getOrderId();
		od = em.find(BookOrders.class, id);

		EntityTransaction txn = em.getTransaction();

		System.out.println("Enter order id");
		int oi = sc.nextInt();
		od.setOrderId(oi);

		System.out.println("Enter reciept name");
		String name = sc.nextLine();
		od.setRecipientName(name);

		System.out.println("Enter receiptent phone");
		String phn = sc.next();
		od.setRecipientphone(phn);

		System.out.println("Enter payement method");
		String paym = sc.next();
		od.setPaymentMethod(paym);

		System.out.println("Enter order Total");
		BigInteger ordt = sc.nextBigInteger();
		od.setOrderTotal(ordt);

		System.out.println("Enter status");
		String stat = sc.next();
		od.setStatus(stat);

		System.out.println("Enter new registerd date(yyyy-MM-dd):");
		LocalDate modifiedDate = LocalDate.parse(sc.next());
		od.setOrderDate(modifiedDate);

		txn.begin();
		em.merge(od);
		txn.commit();
		System.out.println("BookOrder with #" + id + "is modified");
		return od;
	}

	/*
	 * list of partcular book
	 */

	@Override
	public List<BookOrders> viewOrderByBook(Book book) throws BMSException {

		List<BookOrders> ls = new ArrayList<>();
		try {
			int id = book.getBookId();
			String jpql = "SELECT e FROM Bookorders e WHERE EXISTS(SELECT b FROM Books b WHERE b.bookId=?1)";
			TypedQuery<BookOrders> tqry = em.createQuery(jpql, BookOrders.class);
			tqry.setParameter(1, id);
			ls = tqry.getResultList();
		} catch (Exception e) {
			throw new BMSException("Order for book  not found");
		}
		return ls;
	}

}
