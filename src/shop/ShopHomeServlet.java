package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.service.ShopService;
import shop.model.vo.Product;

/**
 * Servlet implementation class ShopHomeServlet
 */
@WebServlet("/shop/home")
public class ShopHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		  List<Product> productList = new ShopService().selectList();
		  System.out.println("공지사항 목록 : " + productList);
		 /* 
		 * request.setAttribute("productList", productList);
		 * request.getRequestDispatcher("/WEB-INF/views/shop/home.jsp").forward(request,
		 * response);
		 */
		
		// 메뉴바 클릭했을 때 페이지로 이동
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/shop/home.jsp");
		request.setAttribute("nav1", "shop");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
