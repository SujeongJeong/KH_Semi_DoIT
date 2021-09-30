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

import qna.model.service.BoardService;
import qna.model.vo.Reply;
import member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/qna/insertReply")
public class QnaReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String reply_content = request.getParameter("reply_content");
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Reply r = new Reply();
		r.setBoard_no(board_no);
		r.setReply_content(reply_content);
		r.setUser_no(writer);
		
		List<Reply> replyList = new BoardService().insertReply(r);

		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
		gson.toJson(replyList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
