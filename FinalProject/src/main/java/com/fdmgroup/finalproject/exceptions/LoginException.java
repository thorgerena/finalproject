package com.fdmgroup.finalproject.exceptions;

/**
 * Exception for login related problems
 * @author Knight
 *
 */
public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginException(String message) {
		super(message);
	}

}
