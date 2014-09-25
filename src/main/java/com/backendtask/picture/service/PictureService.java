package com.backendtask.picture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendtask.model.Picture;
import com.backendtask.monument.dao.IMonumentDAO;
import com.backendtask.picture.dao.IPictureDAO;
import com.backendtask.user.dao.IUserDAO;

/**
 * 
 * Picture Service
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Service
@Transactional(readOnly = true)
public class PictureService implements IPictureService {

	@Autowired
	IPictureDAO pictureDAO;
	
	@Autowired
	IMonumentDAO monumentDAO;
	
	@Autowired
	IUserDAO userDAO;
	
	/**
	 * Add Picture
	 * 
	 * @param  Picture picture
	 */
	@Transactional(readOnly = false)
	public void addPicture(Picture picture, int monumentId, String userName) {
		picture.setMonument(monumentDAO.getMonumentById(monumentId));
		picture.setUser(userDAO.findByUserName(userName));
		getPictureDAO().addPicture(picture);
	}

	/**
	 * Delete Picture
	 * 
	 * @param  Picture picture
	 */
	@Transactional(readOnly = false)
	public void deletePicture(Picture picture, String userName) {
		getPictureDAO().deletePicture(picture);
	}
	
	/**
	 * Update Picture
	 * 
	 * @param  Picture picture
	 */
	@Transactional(readOnly = false)
	public void updatePicture(Picture picture, int monumentId, String userName) {
		picture.setMonument(monumentDAO.getMonumentById(monumentId));
		picture.setUser(userDAO.findByUserName(userName));
		getPictureDAO().updatePicture(picture);
	}
	
	/**
	 * Get Picture
	 * 
	 * @param  int Picture Id
	 */
	public Picture getPictureById(int id) {
		return getPictureDAO().getPictureById(id);
	}

	/**
	 * Get Picture List
	 * 
	 */
	public List<Picture> getPictures(int monumentId, String username) {	
		return getPictureDAO().getPictures(monumentId, username);
	}

	/**
	 * Get Picture DAO
	 * 
	 * @return ICategoryDAO - Picture DAO
	 */
	public IPictureDAO getPictureDAO() {
		return pictureDAO;
	}

	/**
	 * Set Picture DAO
	 * 
	 * @param ICategoryDAO - Picture DAO
	 */
	public void setPictureDAO(IPictureDAO pictureDAO) {
		this.pictureDAO = pictureDAO;
	}

}
