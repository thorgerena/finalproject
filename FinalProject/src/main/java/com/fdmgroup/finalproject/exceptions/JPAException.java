package com.fdmgroup.finalproject.exceptions;

/**
 * Custom exception for JPA related problems
 * @author Knight
 *
 */
public class JPAException extends Exception {
	
	private static final String INPUT_ERROR_NULL = "Input can't be null";
	private static final String INPUT_ERROR_DUPLICATE_KEY = "Primary key already exists";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPAException(String message) {
		super(message);
	}
	
	public static String inputErrorNull() {
		return INPUT_ERROR_NULL;
	}
	
	public static String inputErrorDuplicateKey() {
		return INPUT_ERROR_DUPLICATE_KEY;
	}
}
 