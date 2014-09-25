package com.backendtask.picture.service;

import java.util.List;

import com.backendtask.model.Picture;

/**
 * 
 * Picture Service Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface IPictureService {
	
	/**
	 * Add Picture
	 * 
	 * @param  Picture picture
	 */
	public void addPicture(Picture picture, int monumentId, String userName);
	
	/**
	 * Update Picture
	 * 
	 * @param  Picture picture
	 */
	public void updatePicture(Picture picture, int monumentId, String userName);

	/**
	 * Delete Picture
	 * 
	 * @param  Picture picture
	 */
	public void deletePicture(Picture picture, String userName);
	
	/**
	 * Get Picture
	 * 
	 * @param  int Picture Id
	 */
	public Picture getPictureById(int id);
	
	/**
	 * Get Picture List
	 * 
	 * @return List - Picture list
	 */
	public List<Picture> getPictures(int monumentId, String userName);
}
