package com.chainsys.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.customexception.BlogException;
import com.chainsys.model.User;
import com.chainsys.services.AuthenticationService;
import com.google.gson.Gson;

/**
 * Servlet implementation class Registeration
 */
@WebServlet("/Registeration")
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registeration() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String registrationDetails = request.getParameter("obj");
		Gson gson = new Gson();
		User userDetail = gson.fromJson(registrationDetails, User.class);
		AuthenticationService authenticationService = new AuthenticationService();
		try {
			authenticationService.registerUser(userDetail);
		} catch (BlogException | SQLException b) {
			response.getWriter().write(b.getMessage());
		}
		String userCredential = gson.toJson(userDetail);
		response.getWriter().write(userCredential);
	}

}
