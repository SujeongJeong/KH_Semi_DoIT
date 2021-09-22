package shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import shop.model.service.ShopService;
import shop.model.vo.Product;

/**
 * Servlet implementation class ProductModifyViewServlet
 */
@WebServlet("/productModifyView")
public class ProductModifyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductModifyViewServlet() {
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
		
		Product p = new ShopService().selectProduct(product_no);
		
		
		if(p != null) {
			request.setAttribute("p", p);
			request.getRequestDispatcher("/WEB-INF/views/shop/productModifyView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "수정할 게시글을 불러오는데 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
	}
}
