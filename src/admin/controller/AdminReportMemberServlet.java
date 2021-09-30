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
 * Servlet implementation class AdminReportMemberServlet
 */
@WebServlet("/admin/reportMember")
public class AdminReportMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminReportMemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "admin");

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String category = request.getParameter("category");
		String range = request.getParameter("range");

		Map<String, Object> map = new AdminService().selectReportMemberList(page, new Search(category, range));
		  
		// 응답 페이지 구성 시 사용할 데이터 설정 
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("reportMemberList", map.get("reportMemberList"));
		 
		request.getRequestDispatcher("/WEB-INF/views/admin/reportMemberView.jsp").forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
