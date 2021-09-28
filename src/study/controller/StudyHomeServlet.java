package study.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.model.vo.Member;
import study.model.service.StudyService;
import study.model.vo.MemberJoinStudy;
import study.model.vo.MemberTimerCast;
import study.model.vo.Study;

/**
 * Servlet implementation class StudyHomeServlet
 */
@WebServlet("/study/home")
public class StudyHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav1", "study");
		
		
		//페이징
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		String keyword = request.getParameter("keyword");
		String category = request.getParameter("category");
		String canJoin = request.getParameter("canJoin");
		String param = request.getParameter("param");
		
		if(keyword == null) {
			keyword = "";
		}
		if(category == null) {
			category = "default";
		}
		if(canJoin == null) {
			canJoin = "false";
		}
		if(param == null) {
			param = "";
		}
		
		
		Map<String, Object> map = new StudyService().selectList1(page);

		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("StudyList", map.get("StudyList"));
		
		// 전체 스터디 목록
		List<Study> StudyListAll = (List<Study>) map.get("StudyList");
		// ajax에 줄 조건에 맞는 스터디 목록
		List<Study> StudyList = new ArrayList<>();
		
		
		
		if(param == "param1") {
			for(Study study : StudyListAll) {
				if(study.getS_name().contains(keyword)) {
					StudyList.add(study);
				}
			}
		}
		
		if(param == "param2") {
			for(Study study : StudyListAll) {
				if(study.getCname().equals(category)) {
					StudyList.add(study);
				}
			}
		}
		
		if(param == "param3") {
			for(Study study : StudyListAll) {
				if(study.getStudyMemberNum() < study.getS_to()) {
					StudyList.add(study);
				}
			}
		}
		
		// 스터디방 리스트(페이징x)
//		List<Study> StudyList = new StudyService().selectStudyList();
//		request.setAttribute("StudyList", StudyList);
		
		// 스터디방별 가입된 회원수
		List<Study> StudyMemberList = new StudyService().selectStudyMemberList();
		request.setAttribute("StudyMemberList", StudyMemberList);
		
		// 로그인된 유저의 회원번호로 스터디방 몇개 참가하고 있는지 가져오기
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser != null) {
			int user_no = loginUser.getUserNo();
			MemberJoinStudy userJoinStudyNum = new StudyService().userJoinStudyNum(user_no);
//			System.out.println("userJoinStudyNum : "+ userJoinStudyNum);
			request.setAttribute("userJoinStudyNum", userJoinStudyNum);
		}
		
//		System.out.println(StudyList);
		
		if(request.getParameter("param")!=null) {
		// ajax 응답
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(StudyList, response.getWriter());
		} else {
			RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/home.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
