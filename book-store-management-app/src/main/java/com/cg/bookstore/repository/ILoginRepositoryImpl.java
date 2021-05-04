package com.cg.bookstore.repository;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.User;

public class ILoginRepositoryImpl implements ILoginRepository {
	public final EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
	public final EntityManager em = emf.createEntityManager();
	public final EntityTransaction txn = em.getTransaction();

	/*
	 * to add users in database
	 */
	@Override
	public User addUser(User user) throws BMSException {
		txn.begin();
		em.persist(user);
		txn.commit();
		return user;
	}

	/*
	 * to remove users from database
	 */
	@Override
	public boolean removeUser(User user) throws BMSException {
		int id = user.getUserId();
		user = em.find(User.class, id);
		txn.begin();
		em.remove(user);
		txn.commit();
		System.out.println("Customer data removed successfully...");
		return true;
	}

	/*
	 * tu update user details
	 */
	@Override
	public User updateUser(User user) throws BMSException {
		Scanner scan = new Scanner(System.in);
		int Id = user.getUserId();
		user = em.find(User.class, Id);

		EntityTransaction txn = em.getTransaction();
		System.out.println("enter user id");
		int id = scan.nextInt();
		user.setUserId(id);

		System.out.println("Enter user email");
		String e = scan.next();
		user.setEmail(e);

		System.out.println("Enter password");
		String p = scan.next();
		user.setPassword(p);

		System.out.println("Enter role");
		String r = scan.next();
		user.setRole(r);

		txn.begin();
		em.merge(user);
		txn.commit();
		return user;

	}
}
