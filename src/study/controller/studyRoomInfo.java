package study.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import study.model.service.StudyService;
import study.model.vo.Study;

/**
 * Servlet implementation class studyRoomInfo
 */
@WebServlet("/study/studyInfo")
public class studyRoomInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studyRoomInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클릭한 스터디방의 스터디방 번호 넘긴거 받아오기
		int s_no = Integer.parseInt(request.getParameter("s_no"));

		// 로그인되어있는 loginUser 세션에서 받아와서 다음 페이지에 넘기기 
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		session.setAttribute("loginUser", loginUser);
	
		// 스터디방 조회
		StudyService ss = new StudyService();
		
		Study s = ss.selectStudyRoom(s_no);
		
//		System.out.println(s);		// 양호
		
		request.setAttribute("studyRoom", s);
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/studyRoomInfo.jsp");
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
