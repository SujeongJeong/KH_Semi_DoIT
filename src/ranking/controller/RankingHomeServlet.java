package ranking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import ranking.model.service.RankingService;
import ranking.model.vo.Ranking;
import study.model.service.StudyService;
import study.model.vo.Study;

/**
 * Servlet implementation class RankingHomeServlet
 */
@WebServlet("/ranking")
public class RankingHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = 0;
		if((Member)request.getSession().getAttribute("loginUser") != null) {
			userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		}
		
		// 나의 랭킹 가져오기
		Ranking myRanking = new RankingService().selectMyRanking(userNo);
		
		// 기본값인 전체, 어제 랭킹 불러오기
		List<Ranking> rankinglist = new RankingService().selectYesterday();
		
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
		
		request.setAttribute("nav1", "ranking");
		request.setAttribute("Ranking", rankinglist);
		request.setAttribute("myRanking", myRanking);
		request.getRequestDispatcher("/WEB-INF/views/ranking/ranking.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
