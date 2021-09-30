package shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import shop.model.service.ShopService;
import shop.model.vo.Product;
import shop.model.vo.Purchase;
import study.model.service.StudyService;
import study.model.vo.MemberJoinStudy;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 등록된 상품의 넘버를 불러온다.
		int product_no = Integer.parseInt(request.getParameter("product_no"));

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		int user_no = loginUser.getUserNo();
		// 유저가 구매한 상품의 유효기간의 값.

		Purchase prLimit = new ShopService().purchaseLimit(user_no);

		 if(prLimit.getS_limit() == 0 && prLimit.getS_to_limit() == 0 && prLimit.getTodo_limit() == 0 && prLimit.gets_limit_date() == 0) { prLimit.setS_limit(3); prLimit.setS_to_limit(5);
		 prLimit.setTodo_limit(5); prLimit.sets_limit_date(0); }
		 
		 
		request.setAttribute("prLimit", prLimit);

		Product p = new ShopService().selectProduct(product_no);

		if (p != null) {
			request.setAttribute("p", p);
			request.getRequestDispatcher("/WEB-INF/views/shop/productDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "수정할 게시글을 불러오는데 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}