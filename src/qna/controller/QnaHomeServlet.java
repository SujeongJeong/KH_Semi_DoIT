package qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.NoticeService;
import qna.model.vo.Notice;

/**
 * Servlet implementation class QnaHomeServlet
 */
@WebServlet("/qna/home")
public class QnaHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메뉴바 클릭했을 때 페이지로 이동
		// RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/qna/home.jsp");
		request.setAttribute("nav1", "qna");
		// view.forward(request, response);
		

		List<Notice> noticeList = new NoticeService().selectList();
			
		
		// System.out.println("공지사항 목록 : " + noticeList);
		request.setAttribute("noticeList", noticeList);
		request.getRequestDispatcher("/WEB-INF/views/qna/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
