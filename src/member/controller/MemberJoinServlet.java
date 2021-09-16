package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberJoinServlet
 */
//@WebServlet("/memberJoin")
@WebServlet(name="MemberJoinServlet", urlPatterns = "/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view= request.getRequestDispatcher("WEB-INF/views/member/form/memberJoinForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보 입력 후 회원 가입 버튼을 눌렀을 때 DB에 Insert 처리
		
				// 1. 한글 값이 있을 경유 인코딩 처리
				// request.setCharacterEncoding("UTF-8");
				
				
				// 2. request에 담긴 값 꺼내서 변수에 저장
				String userEmail = request.getParameter("userEmail");
				String userPwd = request.getParameter("userPwd");
				String nickname= request.getParameter("nickname");

				System.out.println("joinForm : " + userEmail + "  " + userPwd);
				
				// 가입 정보를 담은 Member 객체 생성
				Member mem = new Member(userEmail, userPwd, nickname);
				
				 System.out.println(mem);
				
				// 3. 비지니스 로직을 수행할 서비스 메소드로 Member 객체 전달 후 결과 값 리턴 받기
				int result = new MemberService().insertMember(mem);
				
				System.out.println(result);
				
				// 4. 결과 성공/실패 여부에 따라 응답 화면 결정
				if(result > 0) {
					// 메인화면 이동 후 alert로 "회원가입이 완려 되었습니다. 로그인 해주세요." 처리
					// sendRedirect 요청 시  request에 담으면 전달할 수 없음(request 객체가 다시 만들어지므로)
					// session 객체에 해당 메세지 담기
					request.getSession().setAttribute("msg", "회원가입이 완료되었습니다. 로그인 해주세요.");
					// 메인 화면으로 이동, 서버 재요청(sendRedirect)
					// forward 처리 시 /memberJoin에 대한 요청 남아있음
					response.sendRedirect(request.getContextPath());
					
				} else {
					// 회원 가입 실패했을 경우 main 페이지로 이동
					request.getSession().setAttribute("msg", "회원가입 실패!!");
					response.sendRedirect(request.getContextPath());
				}
	}

}
