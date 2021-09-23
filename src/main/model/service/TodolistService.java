package main.model.service;

import java.sql.Connection;
import java.util.List;

import static common.JDBCTemplate.*;

import main.model.dao.TodolistDao;
import main.model.vo.Todolist;


public class TodolistService {
	private TodolistDao td = new TodolistDao();
	
	// 1. todolist 추가 + todolist 새로 갱신
	public List<Todolist> addTodolist(Todolist addTodo) {
		Connection conn = getConnection();
		
		int result = td.addTodolist(conn, addTodo);
		List<Todolist> resultList = null;
		
		if(result > 0) {
			commit(conn);
			resultList = td.selecTodolist(conn, addTodo.getUser_no());
		}else {
			rollback(conn);
		}
			close(conn);
		return resultList;
	}

	// 2. todolist 수정 + todolist 새로 갱신
	public List<Todolist> updateTodolist(Todolist addTodo, String old) {
		Connection conn = getConnection();
		
		int result = td.updateTodolist(conn, addTodo, old);
		List<Todolist> resultList = null;
		
		if(result > 0) {
			commit(conn);
			resultList = td.selecTodolist(conn, addTodo.getUser_no());
		}else {
			rollback(conn);
		}
		close(conn);
		return resultList;
	}
	
	// 3. todolist 삭제 + todlist 새로 갱신
	public List<Todolist> deleteTodolist(Todolist addTodo) {
		Connection conn = getConnection();
		
		int result = td.deleteTodolist(conn, addTodo);
		List<Todolist> resultList = null;
		
		if(result > 0) {
			commit(conn);
			resultList = td.selecTodolist(conn, addTodo.getUser_no());
		}else {
			rollback(conn);
		}
		close(conn);
		return resultList;
	}
	
	// 4. 나의 todolist 조회
		public List<Todolist> selectMyList(int userNo) {
			Connection conn = getConnection();
			List<Todolist> resultList = td.selecTodolist(conn, userNo);
			
			if(resultList != null) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			return resultList;
		}
	
}
