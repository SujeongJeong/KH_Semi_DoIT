package main.model.vo;

import java.util.Date;

public class Todolist {
	/* 
	TODO_DATE		DATE
	TODO_CONTENT	VARCHAR2(100 CHAR)
	USER_NO			NUMBER
	*/
	private Date todo_date;
	private String todo_content;
	private int user_no;
	
	public Todolist() {}

	public Todolist(String todo_content, int user_no) {
		super();
		this.todo_content = todo_content;
		this.user_no = user_no;
	}

	public Todolist(Date todo_date, String todo_content, int user_no) {
		super();
		this.todo_date = todo_date;
		this.todo_content = todo_content;
		this.user_no = user_no;
	}

	public Date getTodo_date() {
		return todo_date;
	}

	public void setTodo_date(Date todo_date) {
		this.todo_date = todo_date;
	}

	public String getTodo_content() {
		return todo_content;
	}

	public void setTodo_content(String todo_content) {
		this.todo_content = todo_content;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "Todolist [todo_date=" + todo_date + ", todo_content=" + todo_content + ", user_no=" + user_no + "]";
	}
	
}
