package com.backendtask.user.dao;

import java.util.List;

import com.backendtask.model.User;

/**
 * 
 * User DAO Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */ 
public interface IUserDAO {

	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addUser(User user);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateUser(User user);
	
	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteUser(User user);
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public User getUserById(int id);
	
	/**
	 * Get User List
	 * 
	 */
	public List<User> getUsers();
	
	User findByUserName(String username);

}