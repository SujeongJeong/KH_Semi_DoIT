package main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static common.JDBCTemplate.*;

import main.model.vo.Todolist;

public class TodolistDao {
	private Properties query = new Properties();
	
	public int addTodolist(Connection conn, Todolist addTodo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("addTodolist");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addTodo.getTodo_content());
			pstmt.setInt(2, addTodo.getUser_no());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
