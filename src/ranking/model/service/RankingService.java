package ranking.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import ranking.model.dao.RankingDao;
import ranking.model.vo.Ranking;

public class RankingService {

	private RankingDao rd = new RankingDao();
	
	// 전체, 전날 랭킹 불러오기
	public List<Ranking> selectYesterday() {
		Connection conn = getConnection();
		
		List<Ranking> resultList = rd.selectYesterday(conn);
		
		if(resultList != null) {

			close(conn);
		} 
		return resultList;
	}
	
	// 나의 랭킹 불러오기
	public Ranking selectMyRanking(int userNo) {
		Connection conn = getConnection();
		
		Ranking my = rd.selectMyRanking(conn, userNo);
		
		if(my != null) close(conn);
		return my;
	}

	// 메인화면 3위까지 랭킹 불러오기
	public List<Ranking> selectThirdRanking() {
		Connection conn = getConnection();
		
		List<Ranking> resultList = rd.selectThirdRanking(conn);
		
		if(resultList != null) {
			close(conn);
		}
			
		return resultList;
	}

}
