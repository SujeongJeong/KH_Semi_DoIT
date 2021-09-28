package admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminDeleteStudyServlet
 */
@WebServlet("/admin/studyDelete")
public class AdminDeleteStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteStudyServlet() {
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
		String[] studyNo = request.getParameterValues("studyNo");

		int[] studyNo2 = new int[studyNo.length];
		for(int i = 0; i < studyNo.length; i++) {
			studyNo2[i] = Integer.parseInt(studyNo[i]);
		}
 		
		int result = 0;
		
		for(int i = 0; i < studyNo2.length; i++) {
			result = new AdminService().studyDelete(studyNo2[i]);
		}
		
		int page = 1;
		
		  Map<String, Object> map = new AdminService().selectStudy(page);
		  
		  request.setAttribute("pi", map.get("pi"));
		  
		  request.setAttribute("studyList", map.get("studyList"));
		 
		
		if(result > 0) {
			request.setAttribute("result", "success");
		} else {
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/admin/studyView.jsp").forward(request, response);
	
	}

}
