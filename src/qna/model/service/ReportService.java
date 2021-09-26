package qna.model.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import qna.model.dao.ReportDao;
import qna.model.vo.Report;



public class ReportService {
	private ReportDao rd = new ReportDao();
	
	// 게시판 신고
	public int boardReport(Report r) {
		Connection conn = getConnection();
		int result = rd.boardReport(conn, r);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

//	// 댓글 신고
//	public int replReport(Report r) {
//		Connection conn = getConnection();
//		int result = rd.replyReport(conn, r);
//		if(result > 0) {
//			commit(conn);
//		} else {
//			rollback(conn);
//		}
//		close(conn);
//		
//		return result;
//	}
	
}
