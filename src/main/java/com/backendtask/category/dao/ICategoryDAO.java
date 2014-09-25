package com.backendtask.category.dao;

import java.util.List;

import com.backendtask.model.Category;

/**
 * 
 * Category DAO Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface ICategoryDAO {

	/**
	 * Add Category
	 * 
	 * @param  Category category
	 */
	public void addCategory(Category category);
	
	/**
	 * Update Category
	 * 
	 * @param  Category category
	 */
	public void updateCategory(Category category);
	
	/**
	 * Delete Category
	 * 
	 * @param  Category category
	 */
	public void deleteCategory(Category category);
	
	/**
	 * Get Category
	 * 
	 * @param  int Category Id
	 */
	public Category getCategoryById(int id);
	
	/**
	 * Get Category List
	 * 
	 */
	public List<Category> getCategories();
}
