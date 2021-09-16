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
		// 파일 첨부 추가 예정
		String studyName = request.getParameter("studyName");
		String studyTO = request.getParameter("studyTO");
		String[] studyDayArr = request.getParameterValues("studyDay");
		String studyDay="";
		if(!studyDayArr[0].equals(""))
			studyDay = String.join(",", studyDayArr);
		String cid = request.getParameter("cid");
		String studyExplain = request.getParameter("studyExplain");
		
		
		Study study = new Study();
	}

}






