package admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import qna.model.vo.Search;

/**
 * Servlet implementation class AdminStudyServlet
 */
@WebServlet("/admin/study")
public class AdminStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStudyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "admin");
		
		int page = 1;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String searchValue = request.getParameter("searchValue");
		
		Map<String, Object> map = new AdminService().selectStudyList(page, new Search(searchValue));
		
		// 응답 페이지 구성 시 사용할 데이터 설정
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("studyList", map.get("studyList"));
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/admin/studyView.jsp").forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
