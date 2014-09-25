package com.backendtask.collection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendtask.collection.dao.ICollectionDAO;
import com.backendtask.model.Collection;
import com.backendtask.model.User;
import com.backendtask.user.dao.IUserDAO;

/**
 * 
 * Collection Service
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Service
@Transactional(readOnly = true)
public class CollectionService implements ICollectionService {

	@Autowired
	ICollectionDAO collectionDAO;
	
	@Autowired
	IUserDAO userDAO;
	
	/**
	 * Add Collection
	 * 
	 * @param  Collection collection
	 */
	@Transactional(readOnly = false)
	public void addCollection(Collection collection, String userName) {
		User user = userDAO.findByUserName(userName);
		collection.setUser(user);
		getCollectionDAO().addCollection(collection);
	}

	/**
	 * Delete Collection
	 * 
	 * @param  Collection collection
	 */
	@Transactional(readOnly = false)
	public void deleteCollection(Collection collection, String userName) {
		User user = userDAO.findByUserName(userName);
		collection.setUser(user);
		getCollectionDAO().deleteCollection(collection);
	}
	
	/**
	 * Update Collection
	 * 
	 * @param  Collection collection
	 */
	@Transactional
	public void updateCollection(Collection collection, String userName) {
		User user = userDAO.findByUserName(userName);
		collection.setUser(user);
		getCollectionDAO().updateCollection(collection);
	}
	
	/**
	 * Get Collection
	 * 
	 * @param  int Collection Id
	 */
	public Collection getCollectionById(int id) {
		return getCollectionDAO().getCollectionById(id);
	}

	/**
	 * Get Collection List for User
	 * 
	 */
	public List<Collection> getCollections(String userName) {	
		return getCollectionDAO().getCollections(userName);
	}

	/**
	 * Get Collection DAO
	 * 
	 * @return ICategoryDAO - Collection DAO
	 */
	public ICollectionDAO getCollectionDAO() {
		return collectionDAO;
	}

	/**
	 * Set Collection DAO
	 * 
	 * @param ICategoryDAO - Collection DAO
	 */
	public void setCollectionDAO(ICollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}

}
