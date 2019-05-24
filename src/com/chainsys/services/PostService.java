package com.chainsys.services;

import java.util.ArrayList;

import com.chainsys.customexception.DuplicateException;
import com.chainsys.dao.CatogeryDAO;
import com.chainsys.dao.PostDAO;
import com.chainsys.model.Catogery;
import com.chainsys.model.Post;

public class PostService {

	public ArrayList<Catogery> displayCatogery() {
		CatogeryDAO catogeryDAO = new CatogeryDAO();
		ArrayList<Catogery> catogeries = catogeryDAO.getCatogery();
		return catogeries;

	}

	public String addPost(Post post) throws Exception {
		String message = "";
		PostDAO postDAO = new PostDAO();
		int statusId = postDAO.newPost(post);
		if (statusId > 0) {
			message = "success";
		} else {
			message = "failed";
		}
		return message;
	}
	public String addCatogery(String catogery) throws DuplicateException{
		String message = "";
		CatogeryDAO catogeryDAO = new CatogeryDAO();
		int statusId = catogeryDAO.addCatogery(catogery);
		if (statusId > 0) {
			message = "success";
		} else {
			message = "failed";
		}
		return message;
	}
}
