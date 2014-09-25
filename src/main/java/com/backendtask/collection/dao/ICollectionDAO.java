package com.backendtask.collection.dao;

import java.util.List;

import com.backendtask.model.Collection;

/**
 * 
 * Collection DAO Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface ICollectionDAO {

	/**
	 * Add Collection
	 * 
	 * @param  Collection collection
	 */
	public void addCollection(Collection collection);
	
	/**
	 * Update Collection
	 * 
	 * @param  Collection collection
	 */
	public void updateCollection(Collection collection);
	
	/**
	 * Delete Collection
	 * 
	 * @param  Collection collection
	 */
	public void deleteCollection(Collection collection);
	
	/**
	 * Get Collection
	 * 
	 * @param  int Collection Id
	 */
	public Collection getCollectionById(int id);
	
	/**
	 * Get Collection List for User
	 * 
	 */
	public List<Collection> getCollections(String userName);
}
