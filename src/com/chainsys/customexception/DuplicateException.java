package com.chainsys.customexception;

public class DuplicateException extends BlogException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6188041786663544454L;

	public DuplicateException(String message) {
		super(message);
	}
}
