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

/**
 * Servlet implementation class RemainCoinServlet
 */
@WebServlet("/remainCoin")
public class RemainCoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemainCoinServlet() {
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

		int product_no = Integer.parseInt(request.getParameter("product_no"));
		int userNo = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
		System.out.println("product_no : " +product_no);
		System.out.println("userNo : " +userNo);
		
		int result = new ShopService().updateCoin(product_no, userNo);
		System.out.println("잔여코인서블렛 : " +result);
		

		if(result > 0) {
			Member loginUser = new MemberService().selectMember(userNo);
			request.getSession().setAttribute("loginUser", loginUser);
			request.getSession().setAttribute("msg", "구매가 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/shop/home");
		}else {
			request.setAttribute("msg", "코인이 부족하여 구매에 실패하였습니다. 코인충전으로 이동합니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}
}
