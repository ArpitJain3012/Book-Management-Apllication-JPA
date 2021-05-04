package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Category;

public interface ICategoryService {
	public Category addCategory(Category cat)throws BMSException;
	public Category editCategory(Category cat)throws BMSException;
	public List<Category> viewAllCategories()throws BMSException;
	public Category removeCategory(Category cat)throws BMSException;

}
