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
import ranking.model.service.RankingService;
import ranking.model.vo.Ranking;
import study.model.service.StudyService;
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
		request.setAttribute("Todolist", myList);
		request.setAttribute("limit", to_limit);
		request.setAttribute("Study", myStudy);
		request.setAttribute("myRanking", myRanking);
		request.setAttribute("rankingList", rankingList);
		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
