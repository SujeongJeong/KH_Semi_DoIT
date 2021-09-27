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

import qna.model.vo.BoardReport;
import qna.model.vo.Notice;
import qna.model.vo.Reply;
import qna.model.vo.ReplyReport;
import qna.model.vo.Report;

public class ReportDao {
	private Properties query = new Properties();
	
	public ReportDao() {
		String fileName = ReportDao.class.getResource("/sql/report/report-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	// 신고
	public int Report(Connection conn, Report r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("report");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReport_content());
			pstmt.setInt(2, r.getUser_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	// boardReport에 값 넣기
	public int boardReportManager(Connection conn, BoardReport br) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("reportBoardManager");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, br.getBoard_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// replyReport에 값 넣기
	public int replyReportManager(Connection conn, ReplyReport rr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("reportReplyManager");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rr.getReply_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	// 댓글 신고 회원 조회
	public int replyReportRefer(Connection conn, Report r, ReplyReport rr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("replyReportRefer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getUser_no());
			pstmt.setInt(2, rr.getReply_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시판 신고 회원 조회
	public int boardReportRefer(Connection conn, Report r, BoardReport br) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("boardReportRefer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getUser_no());
			pstmt.setInt(2, br.getBoard_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}




}
