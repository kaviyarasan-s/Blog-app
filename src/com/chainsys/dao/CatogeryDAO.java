package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.customexception.DuplicateException;
import com.chainsys.model.Catogery;
import com.chainsys.util.ConnectionUtil;

public class CatogeryDAO {

	private PreparedStatement preparedStatement;
	public ArrayList<Catogery> getCatogery() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Catogery> catogeries=null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select catogery_id,catogery from blog_catogery";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			catogeries = new ArrayList<>();
			Catogery catogery = new Catogery();
			while (resultSet.next()) {
				catogery.setId(resultSet.getInt("catogery_id"));
				catogery.setName(resultSet.getString("catogery"));
				catogeries.add(catogery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
				try {
					ConnectionUtil.closeConnection(connection, preparedStatement, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return catogeries;
	}
	public int addCatogery(String catogery) throws DuplicateException {

		Connection connection = null;
		preparedStatement = null;
		int rowCount=0;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into blog_catogery(catogery_id,catogery) values(BLOG_CATOGERYID_SEQ.nextval,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, catogery);
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
				try {
					ConnectionUtil.closeConnection(connection, preparedStatement, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return rowCount;
	}
}
