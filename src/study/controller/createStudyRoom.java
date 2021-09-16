package study.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.model.vo.Study;

/**
 * Servlet implementation class createStudyRoom
 */
@WebServlet("/study/createStudy")
public class createStudyRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createStudyRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/CreateStudy.jsp");
		request.setAttribute("nav1", "study");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 첨부 
		String s_Image = request.getParameter("s_Image");
		
		String s_name = request.getParameter("s_name");
		int s_to = Integer.parseInt(request.getParameter("s_to"));
		String[] s_dayArr = request.getParameterValues("s_day");
		String s_day="";
		if(!s_dayArr[0].equals(""))
			s_day = String.join(",", s_dayArr);
		
		// Date 뽑아오기, time 뽑아오기
		String s_startPeriod = request.getParameter("s_startPeriod");
		String s_endPeriod = request.getParameter("s_endPeriod");
		String s_startTime = request.getParameter("s_startTime");
		String s_endTime = request.getParameter("s_endTime");
		
		String cid = request.getParameter("cid");
		String s_explain = request.getParameter("s_explain");
		String s_notice = request.getParameter("s_notice");
		
		
		//Study study = new Study(s_Image,s_name,s_to,s_day,s_startPeriod,s_endPeriod,
		//		s_startTime,s_endTime,s_explain,s_notice);
		
		Study study = new Study(s_Image,s_name,s_to,s_day,s_explain,s_notice);
	}

}






