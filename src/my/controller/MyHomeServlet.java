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
import study.model.vo.MemberTimerCast;


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
		request.setAttribute("nav1", "my");

		int page = 	1;
		
		// 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 값을 page로 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}	
		
		// 메뉴바 클릭했을 때 페이지로 이동
		if(request.getSession().getAttribute("loginUser") == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		String todayStudyTime = null; 	// 하루 누적 공부시간
		String sumStudyTime = null; // 누적 공부 시간 -> USER_NO 이용
		String lastAvgStudyTime = null; // 최근 30일 주 평균 공부시간
		
		// 오늘 공부 시간
		if(new MyService().todayStudyTime(userNo).equals("0") || new MyService().sumStudyTime(userNo).equals("0") 
				|| new MyService().lastAvgStudyTime(userNo).equals("0") ) {
			todayStudyTime = formatDate("0");
			sumStudyTime = formatDate("0");
			lastAvgStudyTime = formatDate("0");
		} else {
			todayStudyTime = formatDate(new MyService().todayStudyTime(userNo));
			sumStudyTime = formatDate(new MyService().sumStudyTime(userNo));
			lastAvgStudyTime = formatDate(new MyService().lastAvgStudyTime(userNo));
		}
		todayStudyTime = formatDate(new MyService().todayStudyTime(userNo));
		// 일 평균 공부 시간 -> 누적 공부시간 / S_DAY가 NULL이 아닌 컬럼
		sumStudyTime = formatDate(new MyService().sumStudyTime(userNo));
		// 최근 30일 주 평균 공부시간
		lastAvgStudyTime = formatDate(new MyService().lastAvgStudyTime(userNo));
				
		request.setAttribute("todayStudyTime", todayStudyTime);
		request.setAttribute("sumStudyTime", sumStudyTime);
		request.setAttribute("lastAvgStudyTime", lastAvgStudyTime);
		
		Map<String, Object> map = new MyService().selectStudyRecodeList(page, userNo);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("StudyRecodeList", map.get("StudyRecodeList"));
		
		RequestDispatcher view= request.getRequestDispatcher("/WEB-INF/views/my/home.jsp");
		view.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public String formatDate(String sTime) {
		String str = "";
		int time = Integer.parseInt(sTime);
		
		if(time == 0) {
			str = "00:00:00";
		}
		MemberTimerCast mtc = new MemberTimerCast(time);

		String hour = mtc.getHour() < 10 ? "0" + mtc.getHour() : mtc.getHour() +"";
		String minute = mtc.getMinute() < 10 ? "0" + mtc.getMinute() : mtc.getMinute() +"";
		String second = mtc.getSecond() < 10 ? "0" + mtc.getSecond() : mtc.getSecond() +"";
	
		str =  hour + ":" + minute + ":" + second;
		return str;
	} 
}
