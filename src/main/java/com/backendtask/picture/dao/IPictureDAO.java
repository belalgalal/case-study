package com.backendtask.picture.dao;

import java.util.List;

import com.backendtask.model.Picture;

/**
 * 
 * Picture DAO Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface IPictureDAO {

	/**
	 * Add Picture
	 * 
	 * @param  Picture picture
	 */
	public void addPicture(Picture picture);
	
	/**
	 * Update Picture
	 * 
	 * @param  Picture picture
	 */
	public void updatePicture(Picture picture);
	
	/**
	 * Delete Picture
	 * 
	 * @param  Picture picture
	 */
	public void deletePicture(Picture picture);
	
	/**
	 * Get Picture
	 * 
	 * @param  int Picture Id
	 */
	public Picture getPictureById(int id);
	
	/**
	 * Get Picture List
	 * 
	 */
	public List<Picture> getPictures(int monumentId, String username);
}
