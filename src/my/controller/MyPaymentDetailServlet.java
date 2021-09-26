package my.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import my.model.service.MyService;

/**
 * Servlet implementation class MyPaymentDetail
 */
@WebServlet("/my/details")
public class MyPaymentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPaymentDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "my");
		
		int page = 1;
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Map<String, Object> map = new MyService().selectItemList(userNo);
		request.setAttribute("ItemList", map.get("ItemList"));
		
		Map<String, Object> map2 = new MyService().selectPurchaseList(page, userNo);
		
		request.setAttribute("radioValue", "purchase");
		request.setAttribute("pi", map2.get("pi"));
		request.setAttribute("PurchaseList", map2.get("PurchaseList"));
		
		request.getRequestDispatcher("/WEB-INF/views/my/MyDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String radioValue = request.getParameter("details_history");
		int userNo = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
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
		
		Map<String, Object> map4 = new MyService().selectItemList(userNo);
		request.setAttribute("ItemList", map4.get("ItemList"));
		
		/* response.sendRedirect(request.getContextPath()+"/my/details"); */
		request.getRequestDispatcher("/WEB-INF/views/my/MyDetails.jsp").forward(request, response);
		
	}
}
