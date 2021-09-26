package qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.NoticeService;
import qna.model.vo.Reply;

/**
 * Servlet implementation class NoticeReplyUpdateViewServlet
 */
@WebServlet("/noticeReply/updateview")

public class NoticeReplyUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeReplyUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		int reply_no = Integer.parseInt(request.getParameter("reply_no"));	
		String reply_content = request.getParameter("reply_content");
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		
		Reply r = new Reply();
		r.setNotice_no(notice_no);
		r.setReply_no(reply_no);
		r.setReply_content(reply_content);
		r.setUser_no(writer);
		
		List<Reply> replyList = new NoticeService().updateReply(r);
		
		if(replyList != null) {
			request.getSession().setAttribute("msg", "댓글수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/notice/detail?notice_no=" + notice_no);
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
