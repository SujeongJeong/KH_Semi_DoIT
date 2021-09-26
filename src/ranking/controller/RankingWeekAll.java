package ranking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import member.model.vo.Member;
import ranking.model.service.RankingService;
import ranking.model.vo.Ranking;

/**
 * Servlet implementation class RankingWeekAll
 */
@WebServlet("/ranking/WeekAll")
public class RankingWeekAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingWeekAll() {
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
		// 나의 스터디방 리스트
		//List<Study> myStudy = new StudyService().selectMyStudy(userNo);
		// 나의 랭킹 가져오기
		//Ranking myRanking = new RankingService().selectMyRanking(userNo);
		// 기본값인 전체, 어제 랭킹 불러오기
		List<Ranking> rankinglist = new RankingService().selectWeekAll();
		System.out.println(rankinglist);
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().create();
		gson.toJson(rankinglist, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
