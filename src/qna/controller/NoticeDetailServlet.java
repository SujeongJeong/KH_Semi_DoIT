package qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.NoticeService;
import qna.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/notice/detail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "qna");
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		
		Notice notice = new NoticeService().selectNotice(notice_no);
		
		String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserType();
		
		
		String page = "";
		if(notice != null) {
			request.setAttribute("notice", notice);
			page = "/WEB-INF/views/qna/noticeDetailView.jsp";
		} else {
			request.setAttribute("msg", "공지사항 상세 페이지를 불러오는데 실패하였습니다.");
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
