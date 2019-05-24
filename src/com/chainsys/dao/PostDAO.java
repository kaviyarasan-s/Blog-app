package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.model.Catogery;
import com.chainsys.model.Post;
import com.chainsys.util.ConnectionUtil;

public class PostDAO {

	public int newPost(Post post) throws Exception {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO blog_post(post_id,title,content,img_url,catogery_id,user_id) VALUES(blog_postid_seq.nextval,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getContent());
			preparedStatement.setString(3, post.getUrl());
			preparedStatement.setInt(4, post.getCatogery().getId());
			preparedStatement.setInt(5, post.getUserId());
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public ArrayList<Post> displayPosts() {
		Connection connection;
		ArrayList<Post> postList =new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT post_id,title,content,img_url,catogery_id,c.catogery FROM blog_catogery b INNER JOIN catogery c ON (c.catogery = b.catogery)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Post post = new Post();
				Catogery catogery = new Catogery();
				post.setId((resultSet.getInt("post_id")));
				post.setTitle(resultSet.getString("title"));
				post.setContent(resultSet.getString("content"));
				post.setUrl(resultSet.getString("img_url"));
				catogery.setId(resultSet.getInt("catogery_id"));
				catogery.setName(resultSet.getString("c.catogery"));
				post.setCatogery(catogery);
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;

	}
}