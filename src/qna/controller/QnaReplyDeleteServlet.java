package qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.BoardService;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/qnaReply/delete")
public class QnaReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reply_no = Integer.parseInt(request.getParameter("reply_no"));
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		System.out.println("reply_no : " + reply_no);
		System.out.println("board_no : " + board_no);
		
		
		int result = new BoardService().deleteReply(reply_no);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "댓글이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/qna/detail?board_no=" + board_no);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			request.getSession().setAttribute("msg", "댓글 삭제에 실패하였습니다.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}
	

}
