package admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminRefundServlet
 */
@WebServlet("/admin/refund")
public class AdminRefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRefundServlet() {
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
			
			Map<String, Object> map = new AdminService().selectRefundList(page);
			
			// 응답 페이지 구성 시 사용할 데이터 설정
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("refundList", map.get("refundList"));
			
			request.getRequestDispatcher("/WEB-INF/views/admin/refundView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] refundNo = request.getParameterValues("refundNo");
		int[] refundNo2 = new int[refundNo.length];
		for(int i = 0; i < refundNo.length; i++) {
			refundNo2[i] = Integer.parseInt(refundNo[i]);
		}
 		
		int result = 0;
		int result2 = 0;
		
		for(int i = 0; i < refundNo2.length; i++) {
			result = new AdminService().modifyCompleteDate(refundNo2[i]);
			result2 = new AdminService().modifyUserCoin(refundNo2[i]);
		}
		
		int page = 1;
		Map<String, Object> map = new AdminService().selectRefundList(page);
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("refundList", map.get("refundList"));
		
		if(result > 0 && result2 > 0) {
			request.setAttribute("result", "success");
		} else {
			request.setAttribute("result", "fail");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/admin/refundView.jsp").forward(request, response);
	}

}
