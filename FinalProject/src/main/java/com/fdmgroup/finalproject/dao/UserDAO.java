
package com.fdmgroup.finalproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.model.IDAO;
import com.fdmgroup.finalproject.model.User;

/**
 * User Data Access Object connects to the database to create, get, update, or
 * delete a user information
 * 
 * @author Knight
 * @version 1.0
 */
public class UserDAO implements IDAO {

	private EntityManagerFactory emf;

	/**
	 * Constructor for an instance of the User data access object
	 */
	public UserDAO(EntityManagerFactory emf) {

		this.emf = emf;
	}
	
	public UserDAO() {

		this.emf = Persistence.createEntityManagerFactory("finalproject");
	}

	public void addUser(User user) throws JPAException {

		if (user == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		String username = user.getUsername();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();

		if (username == null || password == null || firstName == null
				|| lastName == null || email == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		EntityManager em = emf.createEntityManager();

		if (this.getUser(user.getUsername()) == null) {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
		} else {
			throw new JPAException(JPAException.inputErrorDuplicateKey());
		}
	}

	public User getUser(String username) throws JPAException {

		if (username == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, username);

		em.close();

		return user;
	}

	public void removeUser(String username) throws JPAException {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, username);

		if (user == null) {
			throw new JPAException("Username doesn't exists in database");
		}

		em.getTransaction().begin();

		em.remove(user);
		em.getTransaction().commit();

		em.close();

	}

	public void updateUser(User user) throws JPAException {

		if (user == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		String username = user.getUsername();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();

		if (username == null || password == null || firstName == null
				|| lastName == null || email == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		EntityManager em = emf.createEntityManager();
		User existingUser = em.find(User.class, username);

		if (existingUser == null) {
			throw new JPAException("Username doesn't exists in the database");
		}

		em.getTransaction().begin();

		if (!existingUser.getPassword().equals(password)) {
			existingUser.setPassword(password);
		}
		if (!existingUser.getFirstName().equals(firstName)) {
			existingUser.setFirstName(firstName);
		}
		if (!existingUser.getLastName().equals(lastName)) {
			existingUser.setLastName(lastName);
		}
		if (!existingUser.getEmail().equals(email)) {
			existingUser.setEmail(email);
		}

		em.getTransaction().commit();
		em.close();
	}

	public List<User> listUsers() {

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT e from User e", User.class);

		List<User> users = query.getResultList();

		return users;
	}

	public User getUserByEmail(String email) throws JPAException {

		if (email == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"SELECT e from User e WHERE e.email=:email", User.class);
		query.setParameter("email", email);
		List<User> users = query.getResultList();

		User user = null;

		if (users.size() > 0) {
			user = users.get(0);
		}

		em.close();

		return user;
	}
	
	
}
