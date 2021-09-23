package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.BoardService;
import shop.model.service.ShopService;
import shop.model.vo.Product;

/**
 * Servlet implementation class ProductDelete
 */
@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] deleteProductArr = request.getParameterValues("product_check");
		System.out.println("deleteProductArr : " + deleteProductArr);
		
		int[] deleteProductArrInt = new int[deleteProductArr.length];
		System.out.println("deleteProductArrInt : " + deleteProductArrInt);
		
		
		for(int i=0; i<deleteProductArr.length; i++){
		    deleteProductArrInt[i] = Integer.parseInt(deleteProductArr[i]);
		}

		int result = new ShopService().deleteProduct(deleteProductArrInt);
		
	
		if(result > 0) {
			request.getSession().setAttribute("msg", "상품이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/shop/home");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			request.getSession().setAttribute("msg", "상품 삭제에 실패하였습니다.");
		}
	
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
