package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.service.TodolistService;
import main.model.vo.Todolist;
import member.model.vo.Member;
import my.model.service.MyService;
import ranking.model.service.RankingService;
import ranking.model.vo.Ranking;
import study.model.service.StudyService;
import study.model.vo.MemberTimerCast;
import study.model.vo.Study;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = 0;
		if((Member)request.getSession().getAttribute("loginUser") != null){
			userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		}
		List<Todolist> myList = new TodolistService().selectMyList(userNo);
		List<Study> myStudy = new StudyService().selectMyStudy(userNo);
		int to_limit = new TodolistService().selectTodoLimit(userNo);
		Ranking myRanking = new RankingService().selectMyRanking(userNo);
		List<Ranking> rankingList = new RankingService().selectThirdRanking();	
		
		if(rankingList != null) {
			for(Ranking r : rankingList){
			 String after = r.getS_time().replace("," , ":");
			 r.setS_time(after.trim());
			}
		}
		
		if(myRanking != null) {
			String afterM = myRanking.getS_time().replace(",", ":");
			myRanking.setS_time(afterM.trim());
		}
		
		if(to_limit == 0) to_limit = 5;
		
		String todayStudyTime = null;
		// 오늘 공부 시간
				if(new MyService().todayStudyTime(userNo).equals("0")) {
					todayStudyTime = formatDate("0");

				} else {
					todayStudyTime = formatDate(new MyService().todayStudyTime(userNo));

				}
					todayStudyTime = formatDate(new MyService().todayStudyTime(userNo));
	   System.out.println(todayStudyTime);
	   
	    String myMin = todayStudyTime.substring(3,5);

		String myHour = todayStudyTime.substring(0,2);
		
		request.setAttribute("Todolist", myList);
		request.setAttribute("limit", to_limit);
		request.setAttribute("Study", myStudy);
		request.setAttribute("myRanking", myRanking);
		request.setAttribute("rankingList", rankingList);
		request.setAttribute("myHour", myHour);
		request.setAttribute("myMin", myMin);
		

		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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