package qna.model.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import qna.model.dao.BoardReportDao;
import qna.model.dao.ReportDao;
import qna.model.vo.BoardReport;
import qna.model.vo.Report;



public class BoardReportService {
	private BoardReportDao brd = new BoardReportDao();
	
	// 게시판 신고
	public int boardReport(BoardReport br) {
		Connection conn = getConnection();
		int result = brd.boardReport(conn, br);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
}