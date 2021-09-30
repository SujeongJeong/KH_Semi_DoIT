package study.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.model.service.StudyService;

/**
 * Servlet implementation class memberOutFunction
 */
@WebServlet("/study/enterStudy/memberOutFuncion")
public class memberOutFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberOutFunction() {
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
		
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		System.out.println(s_no);
		System.out.println(user_no);
		int result = new StudyService().deleteMemberJoinStudy(s_no,user_no);
		
		response.setCharacterEncoding("UTF-8");
		
		if(result>0) {
			response.getWriter();
			RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/home.jsp");
			view.forward(request, response);
		} 
		
		
		
	}

}
