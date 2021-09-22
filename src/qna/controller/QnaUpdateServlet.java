package qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.BoardService;
import qna.model.vo.Board;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/qna/update")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int cid = Integer.parseInt(request.getParameter("category"));
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		
		Board b = new Board(board_no, cid, board_title, board_content);
		
		int result = new BoardService().updateBoard(b);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/qna/detail?board_no=" + board_no);
		} else {
			request.setAttribute("msg", "게시글 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
