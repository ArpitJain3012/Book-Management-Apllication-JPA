package com.cg.bookstore.repository;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;

public class ICategoryRepositoryImpl implements ICategoryRepository {
	public final EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
	public final EntityManager em = emf.createEntityManager();
	public final EntityTransaction txn = em.getTransaction();

	Category cate;

	/*
	 * to add category in database
	 */
	@Override
	public Category addCategory(Category cat) {
		EntityManagerFactory emf = com.cg.bookstore.util.JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(cat);
		txn.commit();
		return cat;
	}
	/*
	 * to update category in database
	 */

	@Override
	public Category editCategory(Category cat) {
		Scanner sc = new Scanner(System.in);
		String catname = cat.getCategoryName();
		cat = em.find(Category.class, catname);

		EntityTransaction txn = em.getTransaction();
		System.out.println("Enter category name");
		String name = sc.nextLine();
		cat.setCategoryName(name);

		txn.begin();
		em.merge(cat);
		txn.commit();
		System.out.println("Category with name = " + catname + "  is modified");
		return cat;

	}
	/*
	 * to view list of category data
	 */

	@Override
	public List<Category> viewAllCategories() {
		String jpql = "SELECT cat FROM Category cat";
		TypedQuery<Category> tqry = em.createQuery(jpql, Category.class);
		List<Category> list = tqry.getResultList();
		return list;

	}

	/*
	 * to remove category data from database
	 */
	@Override
	public boolean removeCategory(Category cat) {
		int id = cat.getCategoryId();
		cat = em.find(Category.class, id);
		txn.begin();
		em.remove(cat);
		txn.commit();
		System.out.println("Category data deleted successfully...");
		return true;
	}

}
