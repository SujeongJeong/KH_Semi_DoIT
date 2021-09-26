package admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;
import my.model.service.MyService;

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
		int refundNo = Integer.parseInt(request.getParameter("refund_no"));
		int userNo = Integer.parseInt(request.getParameter("user_no"));
		
		int page = 1;
		
		Map<String, Object> map = new MyService().selectPurchaseList(page, userNo);
		
		request.setAttribute("radioValue", "purchase");
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("PurchaseList", map.get("PurchaseList"));
		
	
		request.getRequestDispatcher("/WEB-INF/views/admin/refundListForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String radioValue = request.getParameter("details_history");
		int userNo = Integer.parseInt(request.getParameter("user_no"));
		
		int page = 	1;
		Map<String, Object> map = null;
		
		request.setAttribute("radioValue", radioValue);
		
		if(radioValue.equals("purchase")) {
			map = new MyService().selectPurchaseList(page, userNo);
			
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("PurchaseList", map.get("PurchaseList"));
			
		} else if(radioValue.equals("charge")) {
			map = new MyService().selectChargeList(page, userNo);
			
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("ChargeList", map.get("ChargeList"));
			
		} else {
			map = new MyService().selectRefundList(page, userNo);
			
			request.setAttribute("pi", map.get("pi"));
			request.setAttribute("RefundList", map.get("RefundList"));
			
		}
		
		request.getRequestDispatcher("/WEB-INF/views/admin/refundListForm.jsp").forward(request, response);
	}

}
