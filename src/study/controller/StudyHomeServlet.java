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

import member.model.vo.Member;
import study.model.service.StudyService;
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
		
		// 스터디방 리스트
		List<Study> StudyList = new StudyService().selectStudyList();
		request.setAttribute("StudyList", StudyList);
		
		// 스터디방별 가입된 회원수
		List<Study> StudyMemberList = new StudyService().selectStudyMemberList();
		request.setAttribute("StudyMemberList", StudyMemberList);
//		System.out.println("StudyMemberList : "+StudyMemberList);
//		System.out.println("StudyMemberList.size() : "+StudyMemberList.size());
		
		// 페이징 처리(기본 1P, 더보기 버튼 클릭시 추가 출력)하기 위해 스터디방 총 갯수 가져오기
		int studyListNumber = new StudyService().selectStudyListNumber();
		request.setAttribute("studyListNumber", studyListNumber);
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/home.jsp");
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
