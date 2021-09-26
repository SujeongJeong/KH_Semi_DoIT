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
import qna.model.service.NoticeService;
import qna.model.vo.Reply;

/**
 * Servlet implementation class NoticeReplyInsertServlet
 */
@WebServlet("/notice/insertReply")
public class NoticeReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 넘어온 파라미터 추출
				int notice_no = Integer.parseInt(request.getParameter("notice_no"));
				String reply_content = request.getParameter("reply_content");
				int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
				
				Reply r = new Reply();
				r.setNotice_no(notice_no);
				r.setReply_content(reply_content);
				r.setUser_no(writer);

				List<Reply> replyList = new NoticeService().insertReply(r);
				
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
