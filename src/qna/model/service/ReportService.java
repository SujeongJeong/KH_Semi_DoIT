package qna.model.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import qna.model.dao.ReportDao;
import qna.model.vo.BoardReport;
import qna.model.vo.ReplyReport;
import qna.model.vo.Report;



public class ReportService {
	private ReportDao rd = new ReportDao();
	
	// 게시판 신고
	public int boardReport(Report r, BoardReport br) {
		Connection conn = getConnection();
		
		int result1 = rd.Report(conn, r);
		int result2 = rd.boardReportManager(conn, br);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1 > 0 && result2 > 0 ? 1 : 0;
	}

	// 댓글 신고
	public int replyReport(Report r, ReplyReport rr) {
		Connection conn = getConnection();
		
		int result1 = rd.Report(conn, r);
		int result2 = rd.replyReportManager(conn, rr);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1 > 0 && result2 > 0 ? 1 : 0;
	}

	// 댓글 신고 회원 조회
	public int replyReportRefer(Report r, ReplyReport rr) {
		Connection conn = getConnection();
		
		int result = rd.replyReportRefer(conn, r, rr);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 게시글 신고 회원 조회
	public int boardReportRefer(Report r, BoardReport br) {
		Connection conn = getConnection();
		
		int result = rd.boardReportRefer(conn, r, br);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 게시판 신고 처리시 회원 신고 횟수 +1
	public int memberBoardReportCount(int board_no) {
		Connection conn = getConnection();
		
		int result = rd.memberBoardReportCount(conn, board_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 댓글 신고 처리시 회원 신고 횟수 +1
	public int memberReplyReportCount(int reply_no) {
		Connection conn = getConnection();
		
		int result = rd.memberReplyReportCount(conn, reply_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	
}
