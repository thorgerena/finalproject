package com.fdmgroup.finalproject.model;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="user_finalp")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userID")
	private int userID;
	@Column(name="username", unique=true)
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email", unique=true)
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="ASSIGNED_COUPONS",
			joinColumns=@JoinColumn(name="userID", referencedColumnName="userID"),
			inverseJoinColumns=@JoinColumn(name="couponID", referencedColumnName="couponID"))
	private List<Coupon> coupons;
	
	/**
	 * Creates a IUser with no parameters
	 */
	public User() {
		super();
	}

	/**
	 * Creates a user with defined parameters
	 * 
	 * @param username
	 *            the username of the IUser
	 * @param password
	 *            the password of the IUser
	 * @param firstName
	 *            the first name of the IUser
	 * @param lastName
	 *            the last name of the IUser
	 * @param email
	 *            the email of the IUser
	 * @param userID 
	 */
	public User(String username, String password, String firstName, String lastName, String email) {
	
		this.username = username;
		this.password = password; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.coupons = new LinkedList<Coupon>();
	}
	
	public int getUserID() {
		return userID;
	}

	/**
	 * Returns the user's username
	 * 
	 * @return the username of the IUser
	 */
	public String getUsername() {
	return username;
	}

	/**
	 * Sets the user's username to the given String value
	 * 
	 * @param username
	 *            the username of the IUser
	 */
	public void setUsername(String username) throws IllegalArgumentException {
		
		if (username == null) {
			throw new IllegalArgumentException("Username cannot be null");
		}		
		this.username = username;
	}

	/**
	 * Returns the user's password
	 * 
	 * @return the password of the IUser
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * Sets the user's password to the given String value
	 * 
	 * @param password
	 *            the password of the IUser
	 */
	public void setPassword(String password) throws IllegalArgumentException {

		if (password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		this.password = password;
	}

	/**
	 * Returns the user's first name
	 * 
	 * @return the first name of the IUser
	 */
	public String getFirstName() {

		return firstName;
	}

	/**
	 * Sets the user's first name to the given String value
	 * 
	 * @param firstName
	 *            the first name of the IUser
	 */
	public void setFirstName(String firstName) throws IllegalArgumentException {

		if (firstName == null) {
			throw new IllegalArgumentException("First name cannot be null");
		}		
		this.firstName = firstName;
	}

	/**
	 * Returns the user's last name
	 * 
	 * @return the last name of the IUser
	 */
	public String getLastName() {

		return lastName;
	}

	/**
	 * Sets the user's last name to the given String value
	 * 
	 * @param lastName
	 *            the last name of the IUser
	 */
	public void setLastName(String lastName) throws IllegalArgumentException {

		if (lastName == null) {
			throw new IllegalArgumentException("Last name cannot be null");
		}	
		this.lastName = lastName;
	}

	/**
	 * Returns the user's email
	 * 
	 * @return the email of the IUser
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Sets the user's email to the given String value
	 * 
	 * @param email
	 *            the email of the IUser
	 */
	public void setEmail(String email) throws IllegalArgumentException {

		if (email == null) {
			throw new IllegalArgumentException("Email cannot be null");
		}
		this.email = email;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
}
