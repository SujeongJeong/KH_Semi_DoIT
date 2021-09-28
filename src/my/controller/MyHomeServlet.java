package my.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import my.model.service.MyService;

/**
 * Servlet implementation class MyHomeServlet
 */
@WebServlet("/my/home")
public class MyHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 	1;
		
		// 메뉴바 클릭했을 때 페이지로 이동
		if(request.getSession().getAttribute("loginUser") == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// 오늘 공부 시간
		String todayStudyTime = new MyService().todayStudyTime(userNo);
		// 일 평균 공부 시간 -> 누적 공부시간 / S_DAY가 NULL이 아닌 컬럼
		String avgStudyTime = new MyService().avgStudyTime(userNo);
		// 누적 공부 시간 -> USER_NO 이용
		String sumStudyTime = new MyService().sumStudyTime(userNo);
		// 최근 30일 주 평균 공부시간
		String lastAvgStudyTime = new MyService().lastAvgStudyTime(userNo);
		
		
		Map<String, Object> map = new MyService().selectStudyRecodeList(page, userNo);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("StudyRecodeList", map.get("StudyRecodeList"));
		
		request.setAttribute("nav1", "my");
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/my/home.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
