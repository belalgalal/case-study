package com.backendtask.monument.service;

import java.util.List;

import com.backendtask.model.Monument;

/**
 * 
 * Monument Service Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface IMonumentService {
	
	/**
	 * Add Monument
	 * 
	 * @param  Monument monument
	 */
	public void addMonument(Monument monument, int collectionId, String userName);
	
	/**
	 * Update Monument
	 * 
	 * @param  Monument monument
	 */
	public void updateMonument(Monument monument, int collectionId, String userName);

	/**
	 * Delete Monument
	 * 
	 * @param  Monument monument
	 */
	public void deleteMonument(Monument monument, String userName);
	
	/**
	 * Get Monument
	 * 
	 * @param  int Monument Id
	 */
	public Monument getMonumentById(int id);
	
	/**
	 * Get Monument List
	 * 
	 * @return List - Monument list
	 */
	public List<Monument> getMonuments(int collectionId, String userName);
}
