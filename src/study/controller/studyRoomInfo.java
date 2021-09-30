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
import study.model.vo.MemberJoinStudy;
import study.model.vo.MemberTimerCast;
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
		Study s = new StudyService().selectStudyRoom(s_no);
		
		System.out.println(s);
		
		int ssp_year = s.getS_startPeriod().getYear()-100;
		int ssp_month = s.getS_startPeriod().getMonth()+1;
		int ssp_date = s.getS_startPeriod().getDate();
		String ssp = "20" + ssp_year + "년 "+ ssp_month + "월 "+ssp_date + "일";
		
		System.out.println(ssp_year);
		System.out.println(ssp_month);
		System.out.println(ssp_date);
		
		int sep_year = s.gets_endPeriod().getYear()-100;
		int sep_month = s.gets_endPeriod().getMonth()+1;
		int sep_date = s.gets_endPeriod().getDate();
		String sep = "20" + sep_year + "년 "+ sep_month + "월 "+sep_date + "일";
		
		int sst_time = s.getS_startTime().getHours();
		int sst_min = s.getS_startTime().getMinutes();
		String sst = sst_time + "시 " + sst_min + "분";
		
		int set_time = s.gets_endTime().getHours();
		int set_min = s.gets_endTime().getMinutes();
		String set = set_time + "시 " + set_min + "분";
		
		System.out.println(ssp);
		System.out.println(sep);
		System.out.println(sst);
		System.out.println(set);
		
		
		
		request.setAttribute("ssp", ssp);
		request.setAttribute("sep", sep);
		request.setAttribute("sst", sst);
		request.setAttribute("set", set);
		
		request.setAttribute("studyRoom", s);
		
		// 스터디방 번호로 해당 스터디방의 정원수 조회
		int StudyMemberCount = new StudyService().StudyMemberCount(s_no);
		request.setAttribute("StudyMemberCount", StudyMemberCount);
//		System.out.println("StudyMemberCount : "+StudyMemberCount);
		
		//클릭한 스터디방 번호와 로그인 되어있는 유저번호로 스터디방 가입유무 조회
		if(loginUser != null) {
			int userNo = loginUser.getUserNo();
			
			MemberJoinStudy mjs = new StudyService().selectMemberJoinStudy(userNo,s_no);
//			System.out.println("스터디방 가입유무(NULL/값) : "+mjs);
			request.setAttribute("selectMemberJoinStudy", mjs);
			
			int memberJoinStudyNum = new StudyService().memberJoinStudyNum(userNo);
//			System.out.println("로그인된 멤버의 가입된 스터디방 수 : "+memberJoinStudyNum);
			request.setAttribute("memberJoinStudyNum", memberJoinStudyNum);

			int userStudyLimit = new StudyService().userStudyLimit(userNo); 
	         
	        request.setAttribute("userStudyLimit", userStudyLimit);
			
			
			
		}
		
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
