package ranking.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDao;
import ranking.model.vo.Ranking;
import static common.JDBCTemplate.*;

public class RankingDao {
	private Properties query = new Properties();
	
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
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				resultList.add(new Ranking(rset.getString("ranking"),
						                   rset.getString("nickname"),
						                   rset.getInt("sumtime"),
						                   rset.getString("profile_img")));
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
				my = new Ranking(rset.getString("ranking"),
								 rset.getString("nickname"),
								 rset.getInt("sumtime"),
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












