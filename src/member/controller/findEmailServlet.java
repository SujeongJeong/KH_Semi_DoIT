package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class findEmailServlet
 */
@WebServlet("/findEmail")
public class findEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view= request.getRequestDispatcher("WEB-INF/views/member/form/findEmailForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = request.getParameter("userEmail");
		String msg = null;
		
		int result = new MemberService().emailCheck(userEmail);
		
		if(result > 0) {
			msg = "가입 정보가 있는 이메일 입니다.";
		} else {
			msg = "가입 정보가 없는 이메일입니다.";
		}
		
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/WEB-INF/views/member/form/findEmailForm.jsp").forward(request, response);
	}
}
