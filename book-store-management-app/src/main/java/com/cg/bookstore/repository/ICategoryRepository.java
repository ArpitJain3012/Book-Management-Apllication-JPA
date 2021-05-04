package com.cg.bookstore.repository;

import java.util.List;

import com.cg.bookstore.entities.Category;

public interface ICategoryRepository {
	public Category addCategory(Category cat);
	public Category editCategory(Category cat);
	public List<Category> viewAllCategories();
	public boolean removeCategory(Category cat);
}
