package qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.ReportService;
import qna.model.vo.BoardReport;
import qna.model.vo.ReplyReport;
import qna.model.vo.Report;

/**
 * Servlet implementation class ReplyReportServlet
 */
@WebServlet("/replyReport")
public class ReplyReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyReportServlet() {
        super();
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int rid = Integer.parseInt(request.getParameter("reply_no"));
		//String rid = request.getParameter("reply_no");
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		
		Report r = new Report(user_no);
		ReplyReport rr = new ReplyReport(rid);
		
		int result =  new ReportService().replyReportRefer(r, rr);
		
		String view = "";
		if(result > 0) {
			view = "WEB-INF/views/qna/ReportForm.jsp";
		}else {
			view = "WEB-INF/views/qna/replyReportForm.jsp?reply_no="+ rid;
		}
		
		request.getRequestDispatcher(view).forward(request, response);			
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String report = request.getParameter("report");
		String etc_comment = request.getParameter("etc_comment");
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		int reply_no = Integer.parseInt(request.getParameter("reply_no"));
		
		String report_content = report + etc_comment;


	
		Report r = new Report(report_content, user_no, reply_no);
		ReplyReport rr = new ReplyReport(reply_no);
		
		int result1 =  new ReportService().replyReport(r, rr);
		
		int result2 = new ReportService().memberReplyReportCount(reply_no);
		
//		System.out.println("rc : " +report_content);
//		System.out.println("etcc : " + etc_comment);
//		System.out.println("user :  " + user_no);
		
		if(result1 > 0 && result2 > 0) { 
				request.setAttribute("result", "success");
				request.getRequestDispatcher("WEB-INF/views/qna/replyReportForm.jsp").forward(request, response);

		} else {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
