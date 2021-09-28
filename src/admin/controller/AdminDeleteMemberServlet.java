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
 * Servlet implementation class AdminDeleteMemberServlet
 */
@WebServlet("/admin/memberDelete")
public class AdminDeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteMemberServlet() {
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
		String[] memberNo = request.getParameterValues("memberNo");

		int[] memberNo2 = new int[memberNo.length];
		for(int i = 0; i < memberNo.length; i++) {
			memberNo2[i] = Integer.parseInt(memberNo[i]);
		}
 		
		int result = 0;
		
		for(int i = 0; i < memberNo2.length; i++) {
			result = new AdminService().memberDelete(memberNo2[i]);
		}
		
		int page = 1;
		
		  Map<String, Object> map = new AdminService().selectMember(page);
		  
		  request.setAttribute("pi", map.get("pi"));
		  
		  request.setAttribute("memberList", map.get("memberList"));
		 
		
		if(result > 0) {
			request.setAttribute("result", "success");
		} else {
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(request, response);
	
	}

}
