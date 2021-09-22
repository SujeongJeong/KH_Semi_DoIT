package shop.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import shop.model.service.ShopService;
import shop.model.vo.Product;

/**
 * Servlet implementation class ProductModifyServlet
 */
@WebServlet("/productModify")
public class ProductModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shop/productModifyView.jsp");
		view.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
		//최대용량제한
		int maxSize = 1024*1024*10;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\shop\\";
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
	
		String files = "/resources/uploadFiles/shop/"+multiRequest.getFilesystemName("file");
		
		Product p = new Product();
		
		int product_no = Integer.parseInt(multiRequest.getParameter("product_no"));
	
		p.setProduct_no(product_no);
		
		p.setProduct_category(multiRequest.getParameter("product_category"));
		p.setProduct_name(multiRequest.getParameter("title"));
		p.setProduct_price(Integer.parseInt(multiRequest.getParameter("price")));
		p.setExpiration_date(Integer.parseInt(multiRequest.getParameter("expirtion")));
		p.setProduct_detail(multiRequest.getParameter("product_category"));
		p.setProduct_img(multiRequest.getFilesystemName("files"));

		
	
		int result = new ShopService().modifyProduct(p);
		System.out.println(result);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/product/detail?product_no=" + product_no);
		} else {
			request.setAttribute("msg", "게시글 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}
	

}
