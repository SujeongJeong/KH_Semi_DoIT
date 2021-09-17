package main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.service.TodolistService;
import main.model.vo.Todolist;
import member.model.vo.Member;

/**
 * Servlet implementation class TodolistServlet
 */
@WebServlet("/main/todolistAdd")
public class TodolistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodolistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addList = request.getParameter("addList");
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Todolist addTodo = new Todolist(addList, userNo);
		
		int result = new TodolistService().addTodolist(addTodo);
		System.out.println(result);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(result);
	}

}


















