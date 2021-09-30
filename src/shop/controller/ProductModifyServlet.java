package shop.controller;

import java.io.File;
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
import member.model.service.MemberService;
import member.model.vo.Member;
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
		
		int maxSize = 1024*1024*10;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\shop\\";
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		String filesname = multiRequest.getFilesystemName("file");
		
			
		//전체 수정로직
		
		int product_no = Integer.parseInt(multiRequest.getParameter("product_no"));
		String category = multiRequest.getParameter("category");
		String title = multiRequest.getParameter("title");
		int price =  Integer.parseInt(multiRequest.getParameter("price"));
		int expirtion = Integer.parseInt(multiRequest.getParameter("expirtion"));
		
		int studyroomlimit = 0;
	      if((multiRequest.getParameter("studyRoomLimit")) != null){
	    	  studyroomlimit = Integer.parseInt(multiRequest.getParameter("studyRoomLimit"));
	      }else {
	    	  studyroomlimit = 3;
	      }
	      
	      int sEntrylimit = 0;
	      if((multiRequest.getParameter("studyroomEntryLimit")) != null){
	    	  sEntrylimit = Integer.parseInt(multiRequest.getParameter("studyroomEntryLimit"));
	      }else {
	    	  sEntrylimit = 5;
	      }   
	      
	      
	      int todoListlimit = 0;
	      if((multiRequest.getParameter("todoListLimit")) != null){
	    	  todoListlimit = Integer.parseInt(multiRequest.getParameter("todoListLimit"));
	      }else {
	    	  todoListlimit = 5;
	      }   
	      
	      int limitproduct = 0;
	      if((multiRequest.getParameter("limitProduct")) != null){
	    	  limitproduct = Integer.parseInt(multiRequest.getParameter("limitProduct"));
	      }else {
	    	  limitproduct = 0;
	      }   
		
		
		String content = multiRequest.getParameter("content");
		
		//파일 조건
		String[] fileimg = new ShopService().selectProduct(product_no).getProduct_img().split("/");
		
		
		if(fileimg.length != 4) {
				File deleteFile = new File(savePath+fileimg[4]);
				deleteFile.delete();
			}
		String files = "/resources/uploadFiles/shop/" + multiRequest.getFilesystemName("modify_file");
		
		Product updateProduct = new ShopService().modifyImg(product_no, files);
			
				if(updateProduct != null) {
					request.setAttribute("msg", "상품 이미지 변경 성공");
				} else {
					
					request.setAttribute("msg", "상품 이미지 변경 실패");
				}
			
				
		//여기서부턴 전체 상품변동
				
		Product p = new Product(product_no, category, title, price, expirtion, content, files, studyroomlimit, sEntrylimit, todoListlimit, limitproduct);

		
		int result = new ShopService().modifyProduct(p);
	
		
		if(result > 0) {
			request.setAttribute("msg", "상품 수정에 성공했습니다.");
			response.sendRedirect(request.getContextPath() + "/productDetail?product_no=" + product_no);
		} else {
			request.setAttribute("msg", "상품 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}
	

}
