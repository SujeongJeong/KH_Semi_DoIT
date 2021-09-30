package study.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class studyMemberManagement
 */
@WebServlet("/study/memberManagement")
public class studyMemberManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studyMemberManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		
		request.setAttribute("user_no", user_no);
		request.setAttribute("s_no", s_no);
		
		
		// 입장한 스터디방에 방장의 회원정보 조회
		Member m = new MemberService().selectMember(user_no);
		request.setAttribute("managerAccount", m);
		
		// 입장한 스터디방에 가입한 회원 목록(방장제외)
		List<Member> MemberList = new MemberService().memberInfoForStudy(s_no,user_no);
		request.setAttribute("MemberList", MemberList);
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/study/studyMemberManagement.jsp");
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
