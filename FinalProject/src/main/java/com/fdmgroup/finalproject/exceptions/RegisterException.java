package com.fdmgroup.finalproject.exceptions;

/**
 * Exception for registration related problems
 * @author Knight
 *
 */
public class RegisterException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterException(String message) {
		super(message);
	}

}
