package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.BoardService;
import qna.model.service.NoticeService;
import qna.model.vo.Board;
import qna.model.vo.Notice;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/qna/detail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * request.setAttribute("nav1", "qna");
		;
		 * 
		 * int nno = Integer.parseInt(request.getParameter("notice_no"));
		 * 
		 * Notice notice = new NoticeService().selectNotice(nno);
		 * 
		 * String page = ""; if(notice != null) { request.setAttribute("notice",
		 * notice); page = "/WEB-INF/views/notice/noticeDetailView.jsp"; } else {
		 * request.setAttribute("msg", "공지사항 상세 페이지를 불러오는데 실패하였습니다."); page =
		 * "/WEB-INF/views/common/errorpage.jsp"; }
		 * 
		 * request.getRequestDispatcher(page).forward(request, response);
		 */
		
		request.setAttribute("nav1", "qna");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		Board board = new BoardService().selectBoard(board_no);
		
		// String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserType();
		
		
		String page = "";
		if(board != null) {
			request.setAttribute("board", board);
			page = "/WEB-INF/views/qna/qnaDetailView.jsp";
		} else {
			request.setAttribute("msg", "게시글 상세 페이지를 불러오는데 실패하였습니다.");
			page = "/WEB-INF/views/common/errorpage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
