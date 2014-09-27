package com.backendtask.monument.dao;

import java.util.List;

import com.backendtask.model.Monument;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * Monument DAO
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Repository
public class MonumentDAO implements IMonumentDAO {
	
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
	 * Add Monument
	 * 
	 * @param  Monument monument
	 */
	public void addMonument(Monument monument) {
		getSessionFactory().getCurrentSession().save(monument);
	}

	/**
	 * Delete Monument
	 * 
	 * @param  Monument monument
	 */
	public void deleteMonument(Monument monument) {
		getSessionFactory().getCurrentSession().delete(monument);
	}

	/**
	 * Update Monument
	 * 
	 * @param  Monument monument
	 */
	public void updateMonument(Monument monument) {
		getSessionFactory().getCurrentSession().update(monument);
	}

	/**
	 * Get Monument
	 * 
	 * @param  int Monument Id
	 * @return Monument 
	 */
	@SuppressWarnings("rawtypes")
	public Monument getMonumentById(int id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from Monument where monumentId=?")
									        .setParameter(0, id).list();
		return (Monument)list.get(0);
	}

	/**
	 * Get Monument List
	 * 
	 * @return List - Monument list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Monument> getMonuments(int collectionId, String userName) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Monument m JOIN FETCH m.category where m.collection.collectionId = ? and m.user.userName = ?")
				.setParameter(0, collectionId).setParameter(1, userName).list();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Monument> findMonuments(String monumentName, String categoryName, String userName) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Monument m JOIN FETCH m.category where m.monumentName like ? and m.category.categoryName like ? and m.user.userName = ?")
				.setParameter(0, "%"+monumentName+"%").setParameter(1, "%"+categoryName+"%").setParameter(2, userName).list();
		return list;
	}
}
