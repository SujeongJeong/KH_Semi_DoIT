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
import qna.model.vo.Reply;
import qna.model.vo.Report;

public class ReportDao {
	private Properties query = new Properties();
	
	public ReportDao() {
		String fileName = ReportDao.class.getResource("/sql/board/board-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	// 게시글 신고
	public int boardReport(Connection conn, Report r) {
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


}
