package ranking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.vo.Member;
import ranking.model.service.RankingService;
import ranking.model.vo.Ranking;

/**
 * Servlet implementation class RankingSelect
 */
@WebServlet("/ranking/selectRanking")
public class RankingSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingSelect() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = 0;
		if((Member)request.getSession().getAttribute("loginUser") != null) {
			userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		}
		String group = request.getParameter("group");
		String period = request.getParameter("period");
		int s_no = Integer.parseInt(request.getParameter("sNum"));

		Ranking myRanking = new Ranking();
		List<Ranking> rankinglist = new ArrayList<>();
		
		if(group.equals("all") && period.equals("yesterday")) {
			rankinglist = new RankingService().selectYesterday();
			myRanking = new RankingService().selectMyRanking(userNo);
				
		}else if(group.equals("all") && period.equals("week")) {
			rankinglist = new RankingService().selectWeekMonthAll(7);
			myRanking = new RankingService().selectMyMonthWeekAll(userNo, 7);
				
		}else if(group.equals("all") && period.equals("month")){
			rankinglist = new RankingService().selectWeekMonthAll(30);
			myRanking = new RankingService().selectMyMonthWeekAll(userNo, 30);
			
		}else if(group.equals("study") && period.equals("yesterday")) {
			rankinglist = new RankingService().selectYesterdayS(s_no);
			myRanking = new RankingService().selectMyRankYesS(userNo, s_no);
			
		}else if(group.equals("study") && period.equals("week")) {
			rankinglist = new RankingService().selectWeekMonthS(s_no, 7);
			myRanking = new RankingService().selectMyRankWeekMonS(userNo, s_no, 7);
			
		}else if(group.equals("study") && period.equals("month")){
			rankinglist = new RankingService().selectWeekMonthS(s_no, 30);
			myRanking = new RankingService().selectMyRankWeekMonS(userNo, s_no, 30);
		}

		if(rankinglist != null) {
			for(Ranking r : rankinglist){
			 String after = r.getS_time().replace("," , ":");
			 r.setS_time(after.trim());
			}
		}
		
		if(myRanking != null) {
			String afterM = myRanking.getS_time().replace(",", ":");
			myRanking.setS_time(afterM.trim());
		}
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		gson.toJson(rankinglist, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
