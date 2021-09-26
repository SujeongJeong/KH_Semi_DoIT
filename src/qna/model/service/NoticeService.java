package qna.model.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import qna.model.dao.NoticeDao;
import qna.model.vo.Board;
import qna.model.vo.Notice;
import qna.model.vo.Reply;


public class NoticeService {
	private NoticeDao nd = new NoticeDao();

	// 1. 공지사항 리스트 조회용 서비스 메소드
	public List<Notice> selectList() {
		Connection conn = getConnection();
		List<Notice> noticeList = nd.selectList(conn);
		close(conn);
		return noticeList;
	}

	// 2. 공지사항 작성용 서비스 메소드
	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = nd.insertNotice(conn, n);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	// 조회수 증가
	public int increaseCount(int notice_no) {
		Connection conn = getConnection();
		
		int result = nd.increaseCount(conn, notice_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

//	// 3. 공지사항 상세 페이지 조회
//	public Notice selectNotice(int notice_no) {
//		Connection conn = getConnection();
//		// 1. 조회수 증가
//		int result = nd.increaseCount(conn, notice_no);
//		
//		// 2. nno에 해당하는 Notice 정보 조회
//		Notice n = null;
//		if(result > 0) {
//			n = nd.selectNotice(conn, notice_no);
//			n.setReplyList(nd.selectReplyList(conn, notice_no));
//			commit(conn);
//		} else {
//			rollback(conn);
//		}
//		
//		close(conn);
//		
//		return n;
//	}
	
	// 3. 공지사항 상세 페이지 조회
	public Notice selectNotice(int notice_no) {
		Connection conn = getConnection();
		
		Notice n = nd.selectNotice(conn, notice_no);
		// 해당 게시글에 대한 댓글 리스트 조회 로직 추가
		n.setReplyList(nd.selectReplyList(conn, notice_no));
		
		close(conn);
		
		return n;
	}

	// 4. 공지사항 수정 페이지 이동을 위한 공지사항 조회
	public Notice selectNoticeNoCnt(int notice_no) {
		Connection conn = getConnection();
		
		
		Notice notice = nd.selectNotice(conn, notice_no);
		
		close(conn);
		
		return notice;
	}

	
	// 5. 공지사항 수정
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = nd.updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 6. 공지사항 목록에서 검색
//	public List<Notice> selectList(String searchCondition, String searchValue){
//		Connection conn = getConnection();
//		
//		List<Notice> noticeList = nd.selectList(conn, searchCondition, searchValue);
//		
//		close(conn);
//		
//		return noticeList;
//	}

	
	// 7. 공지사항 삭제
	public int deleteNotice(int notice_no) {
		Connection conn = getConnection();
		
		int result = nd.deleteNotice(conn, notice_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 댓글 추가 + 새로 생신 된 댓글 리스트 조회
	public List<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = nd.insertReply(conn, r);
		
		List<Reply> replyList = null;
		
		if(result > 0) {
			commit(conn);
			replyList = nd.selectReplyList(conn, r.getNotice_no());
		} else {
			rollback(conn);
		}
		System.out.println("replyList : " + replyList);
		
		return replyList;
		
	}
	
	// 댓글 수정
	public List<Reply> updateReply(Reply r) {
		Connection conn = getConnection();
		
		int result = nd.updateReply(conn, r);
		
		List<Reply> replyList = null;
		
		if(result > 0) {
			commit(conn);
			replyList = nd.selectReplyList(conn, r.getBoard_no());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return replyList;
	}

	// 댓글 삭제
	public int deleteReply(int reply_no) {
		Connection conn = getConnection();
		
		int result = nd.deleteReply(conn, reply_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
