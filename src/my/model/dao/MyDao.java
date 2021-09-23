package my.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import qna.model.vo.Board;
import qna.model.vo.PageInfo;
import study.model.vo.Study;

public class MyDao {
	private Properties query = new Properties();
	
	public MyDao() {
		String fileName = MyDao.class.getResource("/sql/my/my-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	// 게시글 총 개수
	public int getListCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	// 댓글 단 게시글 총 개수
	public int getReplyCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int ReplyListCount = 0;
		String sql = query.getProperty("getReplyListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ReplyListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(ReplyListCount);
		return ReplyListCount;
	}
	
	// 게시글 리스트 
	public List<Board> selectMyBoardList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> MyboardList = new ArrayList<>();
		String sql = query.getProperty("selectMyBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 2;
			
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyboardList.add(new Board(rset.getInt("board_no"),
										rset.getString("cname"),
										rset.getString("board_title"),
										rset.getString("nickname"),
										rset.getDate("create_date"),
										rset.getInt("count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyboardList;
	}
	
	// 댓글을 단 게시글 리스트
	public List<Board> selectMyReplyList(Connection conn, PageInfo pi2, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> MyReplyList = new ArrayList<>();
		String sql = query.getProperty("selectMyReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi2.getPage() - 1) * pi2.getBoardLimit() + 1;
			int endRow = startRow + pi2.getBoardLimit() - 1;
			int paramIndex = 2;
			
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyReplyList.add(new Board(rset.getInt("board_no"),
										rset.getString("cname"),
										rset.getString("board_title"),
										rset.getString("nickname"),
										rset.getDate("create_date"),
										rset.getInt("count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyReplyList;
	}
	
	public int getOpenStudyCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int openStudyCount = 0;
		String sql = query.getProperty("getOpenStudyCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
				openStudyCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return openStudyCount;
	}
	
	public List<Study> selectMyOpenStudyList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> MyOpenStudyList = new ArrayList<>();
		String sql = query.getProperty("selectMyOpenStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 2;
			
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyOpenStudyList.add(new Study(rset.getInt("S_NO"),
										rset.getString("CNAME"),
										rset.getString("S_NAME"),
										rset.getInt("S_TO"),
										rset.getString("NICKNAME"),
										rset.getDate("S_STARTPERIOD"),
										rset.getDate("S_ENDPERIOD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyOpenStudyList;
	}

	public List<Study> selectMyJoinStudyList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> MyJoinStudyList = new ArrayList<>();
		String sql = query.getProperty("selectMyJoinStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyJoinStudyList.add(new Study(rset.getInt("S_NO"),
										rset.getString("CNAME"),
										rset.getString("S_NAME"),
										rset.getInt("S_TO"),
										rset.getString("NICKNAME"),
										rset.getDate("S_STARTPERIOD"),
										rset.getDate("S_ENDPERIOD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyJoinStudyList;
	}
}
