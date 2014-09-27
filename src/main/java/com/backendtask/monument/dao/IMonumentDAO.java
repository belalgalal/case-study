package com.backendtask.monument.dao;

import java.util.List;

import com.backendtask.model.Monument;

/**
 * 
 * Monument DAO Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface IMonumentDAO {

	/**
	 * Add Monument
	 * 
	 * @param  Monument monument
	 */
	public void addMonument(Monument monument);
	
	/**
	 * Update Monument
	 * 
	 * @param  Monument monument
	 */
	public void updateMonument(Monument monument);
	
	/**
	 * Delete Monument
	 * 
	 * @param  Monument monument
	 */
	public void deleteMonument(Monument monument);
	
	/**
	 * Get Monument
	 * 
	 * @param  int Monument Id
	 */
	public Monument getMonumentById(int id);
	
	/**
	 * Get Monument List
	 * 
	 */
	public List<Monument> getMonuments(int collectionId, String userName);

	public List<Monument> findMonuments(String monumentName, String categoryName, String userName);
}
