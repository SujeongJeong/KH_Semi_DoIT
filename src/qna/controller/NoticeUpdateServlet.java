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
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/update")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
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
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		
		Notice n = new Notice(notice_no, notice_title, notice_content);
		
//		System.out.println(notice_no);
//		System.out.println(notice_title);
//		System.out.println(notice_content);
		
		int result = new NoticeService().updateNotice(n);
		
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "공지사항 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/notice/detail?notice_no=" + notice_no);
		} else {
			request.setAttribute("msg", "공지사항 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
