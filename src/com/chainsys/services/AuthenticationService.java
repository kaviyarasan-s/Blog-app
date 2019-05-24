package com.chainsys.services;

import java.sql.SQLException;

import com.chainsys.customexception.DuplicateException;
import com.chainsys.customexception.UserNotFoundException;
import com.chainsys.dao.AuthetincationDAO;
import com.chainsys.model.User;

public class AuthenticationService  {

	public User registerUser(User userDetail) throws DuplicateException, UserNotFoundException, SQLException {
		User user = new User();
		AuthetincationDAO authetincationDAO = new AuthetincationDAO();
		int statusId = authetincationDAO.addUser(userDetail);
		if (statusId > 0) {
			user = authetincationDAO.userIntialization(userDetail);
		} else {
			user = null;
		}
		return user;
	}

	public User login(User userDetail) throws UserNotFoundException {
		AuthetincationDAO authetincationDAO = new AuthetincationDAO();
		return authetincationDAO.userIntialization(userDetail);
	}
}
