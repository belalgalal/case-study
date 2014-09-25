package com.backendtask.collection.dao;

import java.util.List;

import com.backendtask.model.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * Collection DAO
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Repository
public class CollectionDAO implements ICollectionDAO {
	
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
	 * Add Collection
	 * 
	 * @param  Collection collection
	 */
	public void addCollection(Collection collection) {
		getSessionFactory().getCurrentSession().save(collection);
	}

	/**
	 * Delete Collection
	 * 
	 * @param  Collection collection
	 */
	public void deleteCollection(Collection collection) {
		getSessionFactory().getCurrentSession().delete(collection);
	}

	/**
	 * Update Collection
	 * 
	 * @param  Collection collection
	 */
	public void updateCollection(Collection collection) {
		getSessionFactory().getCurrentSession().update(collection);
	}

	/**
	 * Get Collection
	 * 
	 * @param  int Collection Id
	 * @return Collection 
	 */
	@SuppressWarnings("rawtypes")
	public Collection getCollectionById(int id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from Collection where collectionId=?")
									        .setParameter(0, id).list();
		return (Collection)list.get(0);
	}

	/**
	 * Get Collection List
	 * 
	 * @return List - Collection list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Collection> getCollections(String userName) {
		List<Collection> list = getSessionFactory().getCurrentSession().createQuery("from Collection c where c.user.userName=?").setParameter(0, userName).list();
		return list;
	}

}
