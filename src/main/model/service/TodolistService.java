package main.model.service;

import java.sql.Connection;
import static common.JDBCTemplate.*;

import main.model.dao.TodolistDao;
import main.model.vo.Todolist;


public class TodolistService {
	private TodolistDao td = new TodolistDao();
	
	// 1. todolist 추가
	public int addTodolist(Todolist addTodo) {
		Connection conn = getConnection();
		
		int result = td.addTodolist(conn, addTodo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
			close(conn);
		return result;
	}

}
