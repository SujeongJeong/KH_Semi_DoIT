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
 * Servlet implementation class ReportQna
 */
@WebServlet("/qnaReport")
public class QnaReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String bid = request.getParameter("board_no");
		int bid = Integer.parseInt(request.getParameter("board_no"));
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		System.out.println("bid:"+bid);
		System.out.println("user_no:"+user_no);
		Report r = new Report(user_no);
		BoardReport br = new BoardReport(bid);
		
		int result =  new ReportService().boardReportRefer(r, br);
		

		
		String view = "";
		if(result > 0) {
			System.out.println("result:"+result);
			view = "WEB-INF/views/qna/ReportForm.jsp";
		} else {
			view = "WEB-INF/views/qna/qnaReportForm.jsp?board_no="+ bid;
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
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		String report_content = report + etc_comment;


	
		Report r = new Report(report_content, user_no);
		BoardReport br = new BoardReport(board_no);
		
		int result =  new ReportService().boardReport(r, br);
		
		System.out.println("rc : " +report_content);
		System.out.println("etcc : " + etc_comment);
		System.out.println("user :  " + user_no);
		System.out.println("board_no" + board_no);

		
		if(result > 0) {
			request.setAttribute("result", "success");
			request.getRequestDispatcher("WEB-INF/views/qna/qnaReportForm.jsp").forward(request, response);
		} else {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
		
		
	}

}
