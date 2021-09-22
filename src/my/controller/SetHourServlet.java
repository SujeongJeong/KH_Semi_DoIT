package my.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class SetHourServlet
 */
@WebServlet("/my/setHour")
public class SetHourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetHourServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hour = request.getParameter("hour");
		String min = request.getParameter("min");
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		String targetHour = hour + "/" + min;
		
		Member updateMember = new MemberService().setHour(userNo, targetHour);
		
		if(updateMember != null) {
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			request.setAttribute("msg", "목표 공부시간 설정 실패");
		}
		
		response.sendRedirect(request.getContextPath()+"/my/home");
	}

}
