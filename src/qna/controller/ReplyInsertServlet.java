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
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 넘어온 파라미터 추출
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String reply_content = request.getParameter("reply_content");
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		System.out.println(board_no);
		System.out.println(reply_content);
		System.out.println(writer);
		
		Reply r = new Reply();
		r.setBoard_no(board_no);
		r.setReply_content(reply_content);
		r.setUser_no(writer);
		
		// Reply 객체 전달하여 insert 하고 현재 게시글의 replyList 리턴
		List<Reply> replyList = new BoardService().insertReply(r);
		
		// GSON 라이브러리 추가 후 replyList 응답
		// GSON 사용 시 날짜 값 Date 포맷에 대한 컨트롤 가능(GsonBuilder 객체가 가진 기능)
		response.setContentType("application/json; charset=utf-8");
		
		// new Gson().toJson(replyList, response.getWriter());
		// 위와 같이 해왔지만 아래와 같이 gson에 있는 날짜 포맷을 사용해서 보내면 원하는데로 날짜를 바꿔서 사용할 수있다.
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
