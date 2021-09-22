package qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qna.model.service.NoticeService;
import qna.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateView
 */
@WebServlet("/notice/updateView")
public class NoticeUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		
		Notice n = new NoticeService().selectNotice(notice_no);
		
		if(n != null) {
			request.setAttribute("notice", n);
			request.getRequestDispatcher("/WEB-INF/views/qna/noticeUpdateView.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "수정한 게시글을 조회하는데 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
