package com.backendtask.monument.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendtask.category.dao.ICategoryDAO;
import com.backendtask.collection.dao.ICollectionDAO;
import com.backendtask.model.Monument;
import com.backendtask.monument.dao.IMonumentDAO;
import com.backendtask.user.dao.IUserDAO;

/**
 * 
 * Monument Service
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Service
@Transactional(readOnly = true)
public class MonumentService implements IMonumentService {

	@Autowired
	IMonumentDAO monumentDAO;
	
	@Autowired
	IUserDAO userDAO;
	
	@Autowired
	ICollectionDAO collectionDAO;
	
	@Autowired
	ICategoryDAO categoryDAO;
	
	/**
	 * Add Monument
	 * 
	 * @param  Monument monument
	 */
	@Transactional(readOnly = false)
	public void addMonument(Monument monument, int collectionId, String userName) {
		monument.setCollection(collectionDAO.getCollectionById(collectionId));
		monument.setUser(userDAO.findByUserName(userName));
		monument.setCategory(categoryDAO.getCategoryById(monument.getCategory().getCategoryId()));
		getMonumentDAO().addMonument(monument);
	}

	/**
	 * Delete Monument
	 * 
	 * @param  Monument monument
	 */
	@Transactional(readOnly = false)
	public void deleteMonument(Monument monument, String userName) {
		getMonumentDAO().deleteMonument(monument);
	}
	
	/**
	 * Update Monument
	 * 
	 * @param  Monument monument
	 */
	@Transactional(readOnly = false)
	public void updateMonument(Monument monument, int collectionId, String userName) {
		monument.setCollection(collectionDAO.getCollectionById(collectionId));
		monument.setUser(userDAO.findByUserName(userName));
		monument.setCategory(categoryDAO.getCategoryById(monument.getCategory().getCategoryId()));
		getMonumentDAO().updateMonument(monument);
	}
	
	/**
	 * Get Monument
	 * 
	 * @param  int Monument Id
	 */
	public Monument getMonumentById(int id) {
		return getMonumentDAO().getMonumentById(id);
	}

	/**
	 * Get Monument List
	 * 
	 */
	public List<Monument> getMonuments(int collectionId, String userName) {	
		return getMonumentDAO().getMonuments(collectionId, userName);
	}

	/**
	 * Get Monument DAO
	 * 
	 * @return ICategoryDAO - Monument DAO
	 */
	public IMonumentDAO getMonumentDAO() {
		return monumentDAO;
	}

	/**
	 * Set Monument DAO
	 * 
	 * @param ICategoryDAO - Monument DAO
	 */
	public void setMonumentDAO(IMonumentDAO monumentDAO) {
		this.monumentDAO = monumentDAO;
	}

	public List<Monument> findMonuments(String monumentName, String categoryName, String userName) {
		return getMonumentDAO().findMonuments(monumentName, categoryName, userName);
	}
}
