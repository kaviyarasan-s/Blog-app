package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.customexception.DuplicateException;
import com.chainsys.customexception.UserNotFoundException;
import com.chainsys.model.User;
import com.chainsys.util.ConnectionUtil;

public class AuthetincationDAO {

	public int addUser(User userDetail) throws DuplicateException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO blog_registration(user_id,name,email,password,img_url) VALUES(student_id_seq.nextval,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userDetail.getName());
			preparedStatement.setString(2, userDetail.getEmail());
			preparedStatement.setString(3, userDetail.getPassword());
			preparedStatement.setString(4, userDetail.getUrl());
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException s) {
			throw new DuplicateException("failed");
		} finally {
			try {
				ConnectionUtil.closeConnection(connection, preparedStatement, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean success(String username, String password) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Boolean isValue = false;
		String sql = "select user_id from blog_registration where email=? AND password=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getInt("user_id") > 0) {
				isValue = true;
			}
		}
		return isValue;
	}

	public User userIntialization(User userDetail) throws UserNotFoundException {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select user_id,name,email,img_url from blog_registration where email=? and password = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userDetail.getEmail());
			ps.setString(2, userDetail.getPassword());
			String temp = "ZX?Cdjfhjef";
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userDetail.setId(rs.getInt("user_id"));
				userDetail.setName(rs.getString("name"));
				userDetail.setPassword(temp);
				userDetail.setUrl(rs.getString("img_url"));
			}
		} catch (Exception e) {
			throw new UserNotFoundException("failed");
		}
		return userDetail;
	}
}
