package study.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import study.model.service.StudyService;
import study.model.vo.Study;

/**
 * Servlet implementation class EnterStudyRoom
 */
@WebServlet("/study/enterStudy")
public class EnterStudyRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterStudyRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		int loginUserNo = Integer.parseInt(request.getParameter("userNo"));
		Study s = new StudyService().selectStudyRoom(s_no);
		
		request.setAttribute("study", s);
		request.setAttribute("loginUserNo", loginUserNo);
		
		int StudyMemberCount = new StudyService().StudyMemberCount(s_no);
		request.setAttribute("StudyMemberCount", StudyMemberCount);
		
		// 입장한 스터디방에 가입한 회원 목록
		List<Member> MemberList = new MemberService().memberInfoForStudy(s_no);
		
		request.setAttribute("MemberList", MemberList);
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/enterStudyRoom.jsp");
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
