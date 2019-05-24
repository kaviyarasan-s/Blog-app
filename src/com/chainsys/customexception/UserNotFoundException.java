package com.chainsys.customexception;

public class UserNotFoundException extends BlogException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String message) {
		super(message);
	}

}
