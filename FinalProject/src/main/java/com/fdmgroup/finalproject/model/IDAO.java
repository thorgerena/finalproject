package com.fdmgroup.finalproject.model;

import java.util.List;

import com.fdmgroup.finalproject.exceptions.JPAException;
/**
 * The interface provides methods to interact with a database of users
 * 
 * @author Daniel Thor Gerena
 * @version 1.0
 *
 */
public interface IDAO {

	/**
	 * Takes the IUser parameter's fields and adds it to the database
	 * 
	 * @param user
	 *            the IUser object
	 * @throws JPAException 
	 */
	public void addUser(User user) throws JPAException;

	/**
	 * Searches the IUser database for a IUser with provided username
	 * 
	 * @param username
	 *            the username of the IUser
	 * @return
	 */
	public User getUser(String username) throws JPAException;

	/**
	 * Finds and removes the user from the database
	 * 
	 * @param username
	 *            the username of the IUser
	 * @throws JPAException 
	 */
	public void removeUser(String username) throws JPAException;

	/**
	 * Finds and updates the user in the database
	 * 
	 * @param user
	 *            the IUser object
	 * @throws JPAException 
	 */
	public void updateUser(User user) throws JPAException;

	/**
	 * Returns a list of all registered users in the database
	 * 
	 * @return List which contains all the users in the database
	 */
	public List<User> listUsers();

}