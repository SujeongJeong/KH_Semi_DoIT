package ranking.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import ranking.model.dao.RankingDao;
import ranking.model.vo.Ranking;

public class RankingService {

	private RankingDao rd = new RankingDao();
	
	// 어제 나의 랭킹 불러오기
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
	
	// 전체, 전날 랭킹 불러오기
	public List<Ranking> selectYesterday() {
		Connection conn = getConnection();
		
		List<Ranking> resultList = rd.selectYesterday(conn);
		
		if(resultList != null) {

			close(conn);
		} 
		return resultList;
	}
	
	// 최근 7일,30일 랭킹 불러오기(전체)
	public List<Ranking> selectWeekMonthAll(int d) {
		Connection conn = getConnection();
		
		List<Ranking> resultList = rd.selectWeekMonthAll(conn,d);
		
		if(resultList != null) {
			close(conn);
		}
			
		return resultList;
	}

	// 스터디방 어제 랭킹
	public List<Ranking> selectYesterdayS(int s_no) {
		Connection conn = getConnection();
		
		List<Ranking> resultList = rd.selectYesterdayS(conn, s_no);
		
		if(resultList != null) {
			close(conn);
		}
			
		return resultList;
	}

	// 스터디방 7일, 30일 랭킹
	public List<Ranking> selectWeekMonthS(int s_no, int d) {
		Connection conn = getConnection();
		
		List<Ranking> resultList = rd.selectWeekMonthS(conn, s_no, d);
		
		if(resultList != null) {
			close(conn);
		}
			
		return resultList;
	}
	
	// 나의 랭킹 7일, 30일 (전체)
	public Ranking selectMyMonthWeekAll(int userNo, int d) {
		Connection conn = getConnection();
		
		Ranking my = rd.selectMyMonthWeekAll(conn, userNo, d);
		
		if(my != null) close(conn);
		return my;
	}

	// 스터디 방에서 어제 나의 랭킹
	public Ranking selectMyRankYesS(int userNo, int s_no) {
		Connection conn = getConnection();
		
		Ranking my = rd.selectMyRankYesS(conn, userNo, s_no);
		
		if(my != null) close(conn);
		return my;
	}
	
	// 스터디방에서 7일, 30일 나의 랭킹
	public Ranking selectMyRankWeekMonS(int userNo, int s_no, int d) {
		Connection conn = getConnection();
		
		Ranking my = rd.selectMyRankWeekMonS(conn, userNo, s_no, d);
		
		if(my != null) close(conn);
		return my;
	}

	// 나의 오늘 공부시간
	public Ranking selectMyToday(int userNo) {
		Connection conn = getConnection();
		
		Ranking my = rd.selectMyToday(conn, userNo);
		
		if(my != null) close(conn);
		return my;
	}


		
}
