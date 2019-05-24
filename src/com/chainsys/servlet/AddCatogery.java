package com.chainsys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.customexception.BlogException;
import com.chainsys.model.Catogery;
import com.chainsys.model.User;
import com.chainsys.services.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Catogery
 */
@WebServlet("/Catogery")
public class AddCatogery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCatogery() {
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
		String message = "";
		if (!request.getParameter("obj").isEmpty()) {
			String catogery = request.getParameter("obj");
			PostService postService = new PostService();
			Gson gson = new Gson();
			Catogery catogeryDetail =gson.fromJson(catogery, Catogery.class);
			try {
				message = postService.addCatogery(catogeryDetail.getName());
				response.getWriter().write(message);
			} catch (BlogException e) {
				message = "failed";
				response.getWriter().write(message);
			}
		} else {
			message = "failed";
			response.getWriter().write(message);
		}
	}

}
