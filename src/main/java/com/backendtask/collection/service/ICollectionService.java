package com.backendtask.collection.service;

import java.util.List;

import com.backendtask.model.Collection;

/**
 * 
 * Collection Service Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface ICollectionService {
	
	/**
	 * Add Collection
	 * 
	 * @param  Collection collection
	 */
	public void addCollection(Collection collection, String userName);
	
	/**
	 * Update Collection
	 * 
	 * @param  Collection collection
	 */
	public void updateCollection(Collection collection, String userName);

	/**
	 * Delete Collection
	 * 
	 * @param  Collection collection
	 */
	public void deleteCollection(Collection collection, String userName);
	
	/**
	 * Get Collection
	 * 
	 * @param  int Collection Id
	 */
	public Collection getCollectionById(int id);
	
	/**
	 * Get Collection List for User
	 * 
	 * @return List - Collection list
	 */
	public List<Collection> getCollections(String userName);
}
