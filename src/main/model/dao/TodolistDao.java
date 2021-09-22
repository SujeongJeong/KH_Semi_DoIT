package main.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.*;

import main.model.vo.Todolist;

public class TodolistDao {
	private Properties query = new Properties();
	
	public TodolistDao() {
		String fileName = TodolistDao.class.getResource("/sql/main/todolist-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// todolist 조회
	public List<Todolist> selecTodolist(Connection conn, int user_no) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		List<Todolist> resultList = new ArrayList<>();
		String sql = query.getProperty("selectTodolist");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				resultList.add(new Todolist(rset.getInt("todo_no"),
											rset.getString("todo_content")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return resultList;
	}
	
	// todolist 추가
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
	
	// todolist 수정
	public int updateTodolist(Connection conn, Todolist addTodo, String old) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateTodolist");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, addTodo.getTodo_content());
			pstmt.setInt(2, addTodo.getUser_no());
			pstmt.setString(3, old);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// todolist 삭제
	public int deleteTodolist(Connection conn, Todolist addTodo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteTodolist");
		
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


