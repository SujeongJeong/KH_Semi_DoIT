package shop;

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
 * Servlet implementation class ProductAddServlet
 */
@WebServlet("/productAdd")
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/shop/productAddView.jsp");
		request.setAttribute("nav1", "shop");
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
		
		//가져올값 -> 파일의 요소를 elment로 가져오기.
	
		String files = "/resources/uploadFiles/shop/"+multiRequest.getFilesystemName("file");
		 
		String category = multiRequest.getParameter("category");
		String title = multiRequest.getParameter("title");
		int price =  Integer.parseInt(multiRequest.getParameter("price"));
		int expirtion = Integer.parseInt(multiRequest.getParameter("expirtion"));
		String content = multiRequest.getParameter("content");

		
		Product p = new Product(category, title, price, expirtion, content, files);

		int result = new ShopService().insertProduct(p);
		
	
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "상품이 등록되었습니다.");
			response.sendRedirect(request.getContextPath()+"/shop/home");
		}else {
			request.setAttribute("msg", "상품 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
		
	}
	
	
	
	
	
	
	
}
