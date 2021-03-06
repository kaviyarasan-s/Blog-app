package com.chainsys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.model.Catogery;
import com.chainsys.model.Post;
import com.chainsys.services.PostService;
import com.google.gson.Gson;

/**
 * Servlet implementation class BlogPost
 */
@WebServlet("/BlogPost")
public class BlogPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogPost() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PostService postService = new PostService();
		ArrayList<Post> postList=postService.displayPost();
		Gson gson = new Gson();
		String posts = gson.toJson(postList);
		response.getWriter().write(posts);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String message = "";
			if (!request.getParameter("obj").isEmpty()) {
				String postJson = request.getParameter("obj");
				PostService postService = new PostService();
				Gson gson = new Gson();
				Post post = gson.fromJson(postJson, Post.class);
				message = gson.toJson(postService.addPost(post));
				response.getWriter().write(message);
			} else {
				message = "failed";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
