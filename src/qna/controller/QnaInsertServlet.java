package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;

/**
 * Servlet implementation class QnaInsertServlet
 */
@WebServlet("/qna/insert")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "qna");
		
		
		  String usertype =((Member)request.getSession().getAttribute("loginUser")).getUserType();
		  
		  if(usertype.equals("A")) {
			  	request.getRequestDispatcher("/WEB-INF/views/qna/noticeInsertView.jsp").forward(request, response); 
			} else {
				 request.getRequestDispatcher("/WEB-INF/views/qna/qnaInsertView.jsp").forward(request, response); 
			}
		 
		
		// request.getRequestDispatcher("/WEB-INF/views/qna/noticeInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/**
		request.setCharacterEncoding("utf-8");
		 int cid = Integer.parseInt(request.getParameter("category"));
		 String title = request.getParameter("title");
		 String content = request.getParameter("content");
		 
		 System.out.println(cid);
		 System.out.println("제목:"+title);
		 System.out.println("내용:"+content);  
		 **/
	}

}
