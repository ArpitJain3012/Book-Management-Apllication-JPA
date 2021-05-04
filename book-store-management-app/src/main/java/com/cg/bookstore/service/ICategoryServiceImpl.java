package com.cg.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.ICategoryRepository;
import com.cg.bookstore.repository.ICategoryRepositoryImpl;

public class ICategoryServiceImpl implements ICategoryService {

	private ICategoryRepository icr;

	public ICategoryServiceImpl() {
		icr = new ICategoryRepositoryImpl();
	}
	/*
	 * validation for category data inside table
	 */

	public boolean isValidCategoryId(int categoryId) {
		return categoryId != 0 && categoryId > 0;
	}

	public boolean isValidCategoryName(String categoryName) {
		return categoryName != null && (categoryName.length() >= 3 || categoryName.length() <= 20);
	}

	public boolean isValidCategory(Category cat) throws BMSException {
		List<String> errorMessages = new ArrayList<>();
		boolean isValid = true;

		if (cat != null) {
			if (!isValidCategoryId(cat.getCategoryId())) {
				isValid = false;
				errorMessages.add("category id cannot be blank and must be a positive number");
			}
			if (!isValidCategoryName(cat.getCategoryName())) {
				isValid = false;
				errorMessages.add("category name cannot be blank and must be 3 to 20 characters long");
			}
			if (!errorMessages.isEmpty()) {
				throw new BMSException("Invalid category :" + errorMessages);
			}
		} else {
			isValid = false;
			throw new BMSException("Category details are not supplied");
		}
		return isValid;
	}

	/*
	 * calling repository methods
	 */
	@Override
	public Category addCategory(Category cat) throws BMSException {
		if (isValidCategory(cat))
			icr.addCategory(cat);

		return cat;

	}

	@Override
	public Category editCategory(Category cat) throws BMSException {
		return icr.editCategory(cat);

	}

	@Override
	public List<Category> viewAllCategories() throws BMSException {
		return icr.viewAllCategories();
	}

	@Override
	public Category removeCategory(Category cat) throws BMSException {
		icr.removeCategory(cat);
		return cat;
	}

}