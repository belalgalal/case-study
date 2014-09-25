package com.backendtask.category.dao;

import java.util.List;

import com.backendtask.model.Category;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * Category DAO
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Repository
public class CategoryDAO implements ICategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Set Hibernate Session Factory
	 * 
	 * @param SessionFactory - Hibernate Session Factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	/**
	 * Add Category
	 * 
	 * @param  Category category
	 */
	public void addCategory(Category category) {
		getSessionFactory().getCurrentSession().save(category);
	}

	/**
	 * Delete Category
	 * 
	 * @param  Category category
	 */
	public void deleteCategory(Category category) {
		getSessionFactory().getCurrentSession().delete(category);
	}

	/**
	 * Update Category
	 * 
	 * @param  Category category
	 */
	public void updateCategory(Category category) {
		getSessionFactory().getCurrentSession().update(category);
	}

	/**
	 * Get Category
	 * 
	 * @param  int Category Id
	 * @return Category 
	 */
	@SuppressWarnings("rawtypes")
	public Category getCategoryById(int id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from Category where categoryId=?")
									        .setParameter(0, id).list();
		return (Category)list.get(0);
	}

	/**
	 * Get Category List
	 * 
	 * @return List - Category list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Category> getCategories() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Category").list();
		return list;
	}

}
