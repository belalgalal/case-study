package com.backendtask.user.service;

import java.util.List;

import com.backendtask.model.User;
import com.backendtask.model.UserRole;

/**
 * 
 * User Service Interface
 * 
 * @author Belal
 * @since 23 September 2014
 * @version 1.0
 *
 */
public interface IUserService {
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addNewUser(User user, UserRole userRole);
	
	/**
	 * Add UserRole
	 * 
	 * @param UserRole userRole
	 */
	public void addUserRole(UserRole userRole);
	
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
	 * @return List - User list
	 */
	public List<User> getUsers();
}
