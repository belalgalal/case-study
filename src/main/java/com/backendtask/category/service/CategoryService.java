package com.backendtask.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backendtask.model.Category;
import com.backendtask.category.dao.ICategoryDAO;

/**
 * 
 * Category Service
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Repository
@Transactional(readOnly = true)
public class CategoryService implements ICategoryService {

	@Autowired
	ICategoryDAO categoryDAO;
	
	/**
	 * Add Category
	 * 
	 * @param  Category category
	 */
	@Transactional(readOnly = false)
	public void addCategory(Category category) {
		getCategoryDAO().addCategory(category);
	}

	/**
	 * Delete Category
	 * 
	 * @param  Category category
	 */
	@Transactional(readOnly = false)
	public void deleteCategory(Category category) {
		getCategoryDAO().deleteCategory(category);
	}
	
	/**
	 * Update Category
	 * 
	 * @param  Category category
	 */
	@Transactional(readOnly = false)
	public void updateCategory(Category category) {
		getCategoryDAO().updateCategory(category);
	}
	
	/**
	 * Get Category
	 * 
	 * @param  int Category Id
	 */
	public Category getCategoryById(int id) {
		return getCategoryDAO().getCategoryById(id);
	}

	/**
	 * Get Category List
	 * 
	 */
	public List<Category> getCategories() {	
		return getCategoryDAO().getCategories();
	}

	/**
	 * Get Category DAO
	 * 
	 * @return ICategoryDAO - Category DAO
	 */
	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	/**
	 * Set Category DAO
	 * 
	 * @param ICategoryDAO - Category DAO
	 */
	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

}
