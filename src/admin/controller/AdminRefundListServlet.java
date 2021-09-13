package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminRefundList
 */
@WebServlet("/refundList")
public class AdminRefundListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRefundListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "";
		// 세션에 로그인 유저 객체가 없다면
//		if(request.getSession().getAttribute("loginUser") == null) {
//			// 세션에 로그인 유저 객체가 없다면 에러페이지로 이동
//			request.setAttribute("msg", "올바르지 않은 요청입니다.");
//			view = "WEB-INF/views/common/errorpage.jsp";
//		} else {
//			// 비밀번호 변경 창으로 이동
//			view = "WEB-INF/views/qna/qnaReportForm.jsp";
//		}
		view = "WEB-INF/views/admin/refundListForm.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
