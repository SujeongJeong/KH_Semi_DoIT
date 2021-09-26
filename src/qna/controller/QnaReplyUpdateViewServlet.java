package qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import member.model.vo.Member;
import qna.model.service.BoardService;
import qna.model.vo.Reply;

/**
 * Servlet implementation class ReplyUpdateViewServlet
 */
@WebServlet("/qnaReply/updateview")
public class QnaReplyUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int reply_no = Integer.parseInt(request.getParameter("reply_no"));	
		String reply_content = request.getParameter("reply_content");
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Reply r = new Reply();
		r.setBoard_no(board_no);
		r.setReply_no(reply_no);
		r.setReply_content(reply_content);
		r.setUser_no(writer);
		
		List<Reply> replyList = new BoardService().updateReply(r);
		
		if(replyList != null) {
			request.getSession().setAttribute("msg", "댓글수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/qna/detail?board_no=" + board_no);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
