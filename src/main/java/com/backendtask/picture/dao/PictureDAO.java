package com.backendtask.picture.dao;

import java.util.List;

import com.backendtask.model.Picture;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * Picture DAO
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Repository
public class PictureDAO implements IPictureDAO {
	
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
	 * Add Picture
	 * 
	 * @param  Picture picture
	 */
	public void addPicture(Picture picture) {
		getSessionFactory().getCurrentSession().save(picture);
	}

	/**
	 * Delete Picture
	 * 
	 * @param  Picture picture
	 */
	public void deletePicture(Picture picture) {
		getSessionFactory().getCurrentSession().delete(picture);
	}

	/**
	 * Update Picture
	 * 
	 * @param  Picture picture
	 */
	public void updatePicture(Picture picture) {
		getSessionFactory().getCurrentSession().update(picture);
	}

	/**
	 * Get Picture
	 * 
	 * @param  int Picture Id
	 * @return Picture 
	 */
	@SuppressWarnings("rawtypes")
	public Picture getPictureById(int id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from Picture where pictureId=?")
									        .setParameter(0, id).list();
		return (Picture)list.get(0);
	}

	/**
	 * Get Picture List
	 * 
	 * @return List - Picture list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Picture> getPictures(int monumentId, String userName) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Picture p where p.monument.monumentId=? and p.user.userName=?")
				.setParameter(0, monumentId).setParameter(1, userName).list();
		return list;
	}

}
