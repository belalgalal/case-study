package com.backendtask.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendtask.model.User;
import com.backendtask.model.UserRole;
import com.backendtask.user.dao.IUserDAO;

/**
 * 
 * User Service
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

	@Autowired
	IUserDAO userDAO;
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	public void addNewUser(User user, UserRole userRole) {
		getUserDAO().addUser(user);
		getUserDAO().addUserRole(userRole);
	}
	
	/**
	 * Add UserRole
	 * 
	 * @param UserRole userRole
	 */
	public void addUserRole(UserRole userRole) {
		getUserDAO().addUserRole(userRole);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	public void updateUser(User user) {
		getUserDAO().updateUser(user);
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public User getUserById(int id) {
		return getUserDAO().getUserById(id);
	}

	/**
	 * Get User List
	 * 
	 */
	public List<User> getUsers() {	
		return getUserDAO().getUsers();
	}

	/**
	 * Get User DAO
	 * 
	 * @return ICategoryDAO - User DAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Set User DAO
	 * 
	 * @param ICategoryDAO - User DAO
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
