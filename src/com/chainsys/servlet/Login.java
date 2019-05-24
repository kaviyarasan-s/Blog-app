package com.chainsys.servlet;

import java.io.IOException;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("obj").isEmpty())
		{
			String credentail = request.getParameter("obj");
			AuthenticationService authenticationService = new AuthenticationService();
			Gson gson = new Gson();
			User userDetail = gson.fromJson(credentail, User.class);
			try {
				authenticationService.login(userDetail);
				String user=gson.toJson(userDetail);
				response.getWriter().write(user);
			} catch (BlogException e) {
				String message ="not found";
				response.getWriter().write(message);
			}
		}
	}
}
