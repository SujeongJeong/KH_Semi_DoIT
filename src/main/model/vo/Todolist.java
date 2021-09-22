package main.model.vo;

import java.util.Date;

public class Todolist {
	/* 
	TODO_NO	NUMBER
	TODO_DATE	DATE
	TODO_CONTENT	VARCHAR2(100 CHAR)
	USER_NO	NUMBER
	*/
	private int todo_no;
	private Date todo_date;
	private String todo_content;
	private int user_no;
	
	public Todolist() {}

	public Todolist(int todo_no, String todo_content) {
		super();
		this.todo_no = todo_no;
		this.todo_content = todo_content;
	}

	public Todolist(String todo_content, int user_no) {
		super();
		this.todo_content = todo_content;
		this.user_no = user_no;
	}

	public Todolist(int todo_no, Date todo_date, String todo_content, int user_no) {
		super();
		this.todo_no = todo_no;
		this.todo_date = todo_date;
		this.todo_content = todo_content;
		this.user_no = user_no;
	}

	public int getTodo_no() {
		return todo_no;
	}

	public void setTodo_no(int todo_no) {
		this.todo_no = todo_no;
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
		return "Todolist [todo_no=" + todo_no + ", todo_date=" + todo_date + ", todo_content=" + todo_content
				+ ", user_no=" + user_no + "]";
	}

	
}
