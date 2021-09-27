package ranking.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDao;
import ranking.model.vo.Ranking;
import static common.JDBCTemplate.*;

public class RankingDao {
	private Properties query = new Properties();
	private String r_img410 = "/resources/images/flag4.png";
	private String[] rank_img = {"/resources/images/flag1.png","/resources/images/flag2.png","/resources/images/flag3.png",
								 r_img410,r_img410,r_img410,r_img410,r_img410,r_img410,r_img410};
	
	public RankingDao() {
		String fileName = MemberDao.class.getResource("/sql/ranking/ranking-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	// 전체, 전날 랭킹 불러오기
	public List<Ranking> selectYesterday(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Ranking> resultList = new ArrayList<>();
		String sql = query.getProperty("selectYesterday");
		int i= 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 10);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				resultList.add(new Ranking(rset.getInt("to_number(rownum)"),
						                   rset.getString("nickname"),
						                   rset.getString("sumtime"),
						                   rset.getString("profile_img"),
						                   rank_img[i]));
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return resultList;
	}
	
	// 나의 랭킹 불러오기
	public Ranking selectMyRanking(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectMyRanking");
		Ranking my = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				my = new Ranking(rset.getInt("ranking"),
								 rset.getString("nickname"),
								 rset.getString("sumtime"),
								 rset.getString("profile_img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return my;
	}
	
	// 전 날 3위까지 랭킹 불러오기
	public List<Ranking> selectThirdRanking(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Ranking> resultList = new ArrayList<>();
		String sql = query.getProperty("selectYesterday");
		String[] rImg = {"/resources/images/flag1.png","/resources/images/flag2.png","/resources/images/flag3.png"};
		int i=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 3);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				resultList.add(new Ranking(rset.getInt("to_number(rownum)"),
						                   rset.getString("nickname"),
						                   rset.getString("sumtime"),
						                   rset.getString("profile_img"),
						                   rImg[i]));
					i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return resultList;
	}
	
	// 최근 7일 ,30일 랭킹 (전체)
		public List<Ranking> selectWeekMonthAll(Connection conn, int d) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Ranking> resultList = new ArrayList<>();
			String sql = query.getProperty("selectWeekMonthAll");
			int i= 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, d);
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					resultList.add(new Ranking(rset.getInt("to_number(rownum)"),
							                   rset.getString("nickname"),
							                   rset.getString("sumtime"),
							                   rset.getString("profile_img"),
							                   rank_img[i]));
					i++;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}

			return resultList;
		}
		
		// 스터디방 어제 랭킹
		public List<Ranking> selectYesterdayS(Connection conn, int s_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Ranking> resultList = new ArrayList<>();
			String sql = query.getProperty("selectYesterdayS");
			int i= 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_no);
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					resultList.add(new Ranking(rset.getInt("to_number(rownum)"),
							                   rset.getString("nickname"),
							                   rset.getString("sumtime"),
							                   rset.getString("profile_img"),
							                   rank_img[i]));
					i++;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}

			return resultList;
		}
		
		// 스터디방 7일,30일 랭킹
		public List<Ranking> selectWeekMonthS(Connection conn, int s_no, int d) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Ranking> resultList = new ArrayList<>();
			String sql = query.getProperty("selectWeekMonthS");
			int i= 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, d);
				pstmt.setInt(2, s_no);
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					resultList.add(new Ranking(rset.getInt("to_number(rownum)"),
							                   rset.getString("nickname"),
							                   rset.getString("sumtime"),
							                   rset.getString("profile_img"),
							                   rank_img[i]));
					i++;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}

			return resultList;
		}
		
		// 나의 랭킹 7일, 30일 (전체)
		public Ranking selectMyMonthWeekAll(Connection conn, int userNo, int d) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = query.getProperty("selectMyMonthWeekAll");
			Ranking my = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, d);
				pstmt.setInt(2, userNo);
				rset= pstmt.executeQuery();
				
				if(rset.next()) {
					my = new Ranking(rset.getInt("ranking"),
									 rset.getString("nickname"),
									 rset.getString("sumtime"),
									 rset.getString("profile_img"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return my;
		}
		
		// 스터디방 내 어제 나의 랭킹
		public Ranking selectMyRankYesS(Connection conn, int userNo, int s_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = query.getProperty("selectMyRankYesS");
			Ranking my = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_no);
				pstmt.setInt(2, userNo);
				rset= pstmt.executeQuery();
				
				if(rset.next()) {
					my = new Ranking(rset.getInt("ranking"),
									 rset.getString("nickname"),
									 rset.getString("sumtime"),
									 rset.getString("profile_img"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return my;
		}
		
		// 스터디방에서 7일 ,30일 나의 랭킹
		public Ranking selectMyRankWeekMonS(Connection conn, int userNo, int s_no, int d) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = query.getProperty("selectMyRankWeekMonS");
			Ranking my = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, d);
				pstmt.setInt(2, s_no);
				pstmt.setInt(3, userNo);
				rset= pstmt.executeQuery();
				
				if(rset.next()) {
					my = new Ranking(rset.getInt("ranking"),
									 rset.getString("nickname"),
									 rset.getString("sumtime"),
									 rset.getString("profile_img"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return my;
		}
		
		

}












