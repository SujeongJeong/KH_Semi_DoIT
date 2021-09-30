package shop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import shop.model.service.ShopService;
import shop.model.vo.Product;
import shop.model.vo.Purchase;

/**
 * Servlet implementation class ProductOrderServlet
 */
@WebServlet("/productOrder")
public class ProductOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOrderServlet() {
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
		//상품번호, 상품가격, 상품이름, 유효기간. 
		
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		int userNo = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		int result = new ShopService().insertOrder(product_no, userNo);//상품구매용 서블릿
		
		if(result > 0) {
			int result1 = new ShopService().orderAfterCoin(product_no, userNo); //상품을 구매한 후 남은 코인
			if(result1 > 0) {
				Member loginUser = new MemberService().selectMember(userNo);
				request.getSession().setAttribute("loginUser", loginUser);
				request.setAttribute("result", "success");
				
			}
		}else {
			request.setAttribute("result", "fail");
		
		}
		
	
		request.getRequestDispatcher("/WEB-INF/views/shop/productDetailView.jsp").forward(request, response);
		
	}

}
