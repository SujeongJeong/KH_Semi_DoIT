package qna.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static common.JDBCTemplate.close;
import qna.model.vo.Notice;

public class NoticeDao {
	private Properties query = new Properties();
	
	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// 1. 공지사항 목록 조회
	public List<Notice> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> noticeList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
					
//			while(rset.next()) {
//				noticeList.add(new Notice(rset.getInt("notic_no"),
//										  rset.getString("notice_title"),
//										  rset.getString("ncotice_content"),
//										  rset.getInt("count"),
//										  rset.getDate("create_date"),
//										  rset.getDate("modify_date"),
//										  rset.getString("user_no") ,
//										  rset.getString("status")));
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return noticeList;
	}
	
	// 2. 공지사항 글 작성
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNotice_title());
			pstmt.setString(2, n.getNotice_content());
			pstmt.setInt(3, n.getUser_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	// 3. 게시글 조회 시 조회수 증가
	public int increaseCount(Connection conn, int notice_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 4. 게시글 1개 조회
	public Notice selectNotice(Connection conn, int notice_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String sql = query.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			
			rset = pstmt.executeQuery();
			
//			if(rset.next()) {
//				n = new Notice(rset.getInt("notic_no"),
//							  rset.getString("notice_title"),
//							  rset.getString("ncotice_content"),
//							  rset.getInt("count"),
//							  rset.getDate("create_date"),
//							  rset.getDate("modify_date"),
//							  rset.getString("user_no") ,
//							  rset.getString("status")));
//			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	// 공지사항 수정
	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateNotice");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNotice_title());
			pstmt.setString(2, n.getNotice_content());
			pstmt.setInt(3, n.getNotice_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 공지사항 목록에서 검색
	public List<Notice> selectList(Connection conn, String searchCondition, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> noticeList = new ArrayList<>();
		// 기본 sql문 : 검색과 무관한 전체 요청(기본 게시물 보여주기)
		String sql = query.getProperty("selectList");
		
		if(searchCondition.equals("title")) {
			// 제목으로 검색할 경우 수행할 sql문으로 변경
			sql = query.getProperty("selectTitleList");
		} else if(searchCondition.equals("content")){
			// 내용으로 검색할 경우 수행할 sql문으로 변경
			sql = query.getProperty("selectContentList");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(searchCondition.equals("title") || searchCondition.equals("content"))
				pstmt.setString(1, searchValue);
				
			rset = pstmt.executeQuery();
			
//			while(rset.next()) {
//				noticeList.add(new Notice(rset.getInt("notice_no"),
//										  rset.getString("notice_title"),
//										  rset.getString("notice_content"),
//										  rset.getInt("count"),
//										  rset.getDate("create_date"),
//										  rset.getDate("modify_date"),
//										  rset.getString("status"),
//										  rset.getString("user_no")));
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return noticeList;
	}

	// 공지사항 삭제
	public int deleteNotice(Connection conn, int notice_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
