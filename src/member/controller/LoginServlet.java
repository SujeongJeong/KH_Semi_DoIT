package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메뉴바의 로그인을 클릭했을 때 로그인 페이지로 이동
		RequestDispatcher view= request.getRequestDispatcher("WEB-INF/views/member/form/loginForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송 값에 한글 값이 있는 경우 인코딩 처리가 필요하지만
		// 로그인 시에 전송 받는 값은 한글이 없기 때문에 인코딩 처리 하지 않아도 무방
		
		// 1. 요청에 포함된 id, pwd 값
		String userEmail = request.getParameter("userEmail");
		String userPwd = request.getParameter("userPwd");
		
		// 2. 비즈니스 로직 처리하는 서비스 클래스의 해당 메소드를 실행 후 처리 결과를 리턴 받음
		// id,pwd를 가지고 DB로 가서 일치하는 member가 있을 경우 member 객체를 리턴
		Member loginUser = new MemberService().loginMember(userEmail, userPwd);
		
		// System.out.println(loginUser);
		
		// 3. 요청에 대한 결과를 통해 응답을 결정한다
		if(loginUser != null) {
			// loginUser에 대한 정보를 어딘가에 담아서 보내줘야 하는데 담을 수 있는 공간은 다음과 같은
			// 1. application -> 어플리케이션 단위
			// 2. session -> 브라우저 단위
			// 3. request -> 요청 단위
			// 4. page -> 해당 페이지 내
			
			// 위의 4개의 영역 모두 setArrtibute(name, object)를 이용해 객체를 저장할 수 있음
			// 꺼낼 때는 getArrtibute(name)
			// 삭제할 때는 removeAttribute(name)
			
			// Session 객체 : 웹 브라우저당 하나씩 존재하는 객체로 Session에 로그인 한 회원 객체를 등록
			// 해놓으면 어떤 페이지에서는 Session 담긴 회원 객체에 대한 정보를 사용할 수 있음
			
			// 해당 클라이언트에 대한 세션 객체 가져오기
			HttpSession session = request.getSession();
			
			// 초 단위로 해당 세션의 유효기간 설정 가능
			// 10분 뒤 자동 로그아웃이 되게 하려면?
			// session.setMaxInactiveInterval(600);
			
			// 세션 객체에 로그인한 유저의 정보를 담음 => 세션이 유지되는 동안 어디에서든 loginUser의 정보 얻을 수 있음
			session.setAttribute("loginUser", loginUser);

			// 로그인 완료 후 다시 메인 페이지로 이동하기 위해
			// sendRedirect 요청 (서버로 재요청) -> login 요청은 사라짐
			response.sendRedirect(request.getContextPath());
			// response.getContextPath() -> jsp
		}
	}
}
