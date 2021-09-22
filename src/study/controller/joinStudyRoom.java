package study.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.model.service.StudyService;
import study.model.vo.MemberJoinStudy;

/**
 * Servlet implementation class joinStudyRoom
 */
@WebServlet("/study/joinStudy")
public class joinStudyRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinStudyRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		//response.sendRedirect(request.getContextPath() + "/study/studyInfo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		
		MemberJoinStudy mjs = new MemberJoinStudy(userNo,s_no);

		int result = new StudyService().insertMemberJoinStudy(mjs);
		
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/study/studyInfo?s_no="+mjs.getS_no());
			
		}
	}

}







