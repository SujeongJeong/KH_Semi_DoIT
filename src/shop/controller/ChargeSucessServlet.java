package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import shop.model.service.ShopService;
import shop.model.vo.Charge;
import shop.model.vo.Product;

/**
 * Servlet implementation class ChargeSucessServlet
 */
@WebServlet("/shop/chargeSucess")
public class ChargeSucessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargeSucessServlet() {
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
		int charge_coin = Integer.parseInt(request.getParameter("amount"))/110;
		int userNo = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
				
		Charge c = new Charge(charge_coin, userNo);
		
		int result = new ShopService().insertCharge(c);

		if(result > 0) {
			
				Member loginUser = new MemberService().selectMember(userNo);
				request.getSession().setAttribute("loginUser", loginUser);
				request.setAttribute("result", "success");
				
			request.getSession().setAttribute("msg", "충전이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/shop/home");
		}else {
			request.setAttribute("msg", "충전에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		 }
	  }	
		
	
			
}


