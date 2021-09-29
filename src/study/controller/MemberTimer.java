package study.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberTimer
 */
@WebServlet("/study/enterStudy/timer")
public class MemberTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberTimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int dbSaveTime = Integer.parseInt(request.getParameter("dbSaveTime"));
		int loginUserNo = Integer.parseInt(request.getParameter("loginUserNo"));
		
		System.out.println(dbSaveTime);
		System.out.println(loginUserNo);
		
		
		
		
		
		
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter();
	
	
	}

}
