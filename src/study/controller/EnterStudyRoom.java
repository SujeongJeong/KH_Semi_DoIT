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
		
		request.setAttribute("s_no", s_no);
		request.setAttribute("study", s);
		request.setAttribute("loginUserNo", loginUserNo);
		
		int StudyMemberCount = new StudyService().StudyMemberCount(s_no);
		request.setAttribute("StudyMemberCount", StudyMemberCount);
		
		// 스터디방 번호로 해당 스터디방의 개설자 회원번호 가져오기
		Study s2 = new StudyService().selectStudyRoom(s_no);
		request.setAttribute("studyCreater", s2);
		
		int createRoomUser_no = s2.getUser_no();
		
		// 입장한 스터디방에 방장의 회원정보 조회
		Member m = new MemberService().selectMember(createRoomUser_no);
		request.setAttribute("CreateRoomUser", m);
		
		// 입장한 스터디방에 가입한 회원 목록(방장제외)
		List<Member> MemberList = new MemberService().memberInfoForStudy(s_no,createRoomUser_no);
		request.setAttribute("MemberList", MemberList);
		
//		System.out.println(MemberList);
//		System.out.println(s2);
		
		
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
