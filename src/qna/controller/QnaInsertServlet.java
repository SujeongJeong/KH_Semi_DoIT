package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.BoardService;
import qna.model.service.NoticeService;
import qna.model.vo.Board;
import qna.model.vo.Notice;

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
		  
		  System.out.println("usertype = " + usertype);
		  
		  if(usertype.equals("A")) {
			  	request.getRequestDispatcher("/WEB-INF/views/qna/noticeInsertView.jsp").forward(request, response); 
			}else if(usertype.equals("U")) {
				request.getRequestDispatcher("/WEB-INF/views/qna/qnaInsertView.jsp").forward(request, response); 
			} else {
				request.setAttribute("msg", "로그인이 필요합니다.");
				request.getRequestDispatcher("/WEB-INF/views/member/form/loginForm.jsp").forward(request, response); 
			}
		 
		
		// request.getRequestDispatcher("/WEB-INF/views/qna/noticeInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		 int cid = Integer.parseInt(request.getParameter("category"));
		 String title = request.getParameter("title");
		 String content = request.getParameter("content");
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		 
//		 System.out.println(cid);
//		 System.out.println("제목:"+title);
//		 System.out.println("내용:"+content);
		 
		 Board b = new Board(cid, title, content, writer);
		 
		 int result = new BoardService().insertBoard(b);
		 
//		 System.out.println("result :" + result);
			
		 if(result > 0) { 
				request.getSession().setAttribute("msg","게시글이 성공적으로 등록 되었습니다."); 
				response.sendRedirect(request.getContextPath() +"/qna/home"); 
			} else {
				request.setAttribute("msg", "게시글 등록에 실패하였습니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			}
		 
//		 response.setHeader("Cache-Control","no-cache");
//		    response.setHeader("Pragma","no-cache");
	}

}
