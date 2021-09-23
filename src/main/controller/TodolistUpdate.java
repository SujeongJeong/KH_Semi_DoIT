package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.model.service.TodolistService;
import main.model.vo.Todolist;
import member.model.vo.Member;

/**
 * Servlet implementation class TodolistUpdate
 */
@WebServlet("/main/todolistUpdate")
public class TodolistUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodolistUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String old = request.getParameter("old");
		String update = request.getParameter("update");
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
	
		Todolist addTodo = new Todolist();
		addTodo.setTodo_content(update);
		addTodo.setUser_no(userNo);

		// update 후 todolist 리턴
		List<Todolist> resultList = new TodolistService().updateTodolist(addTodo, old);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().create();
		gson.toJson(resultList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
