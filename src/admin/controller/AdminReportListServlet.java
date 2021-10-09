package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import qna.model.vo.Report;

/**
 * Servlet implementation class AdminReportList
 */
@WebServlet("/reportList")
public class AdminReportListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReportListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int br_no =  Integer.parseInt(request.getParameter("br_no"));
		String type =  request.getParameter("type");
		

		List<Report> r = null;
		
		if(type.equals("게시글")) {
			r = new AdminService().selectBoardReportList(br_no);			
		} else {
			r = new AdminService().selectReplyReportList(br_no);
		}
		
		request.setAttribute("reportList", r);
	
		request.getRequestDispatcher("WEB-INF/views/admin/reportListForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
