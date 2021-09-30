package qna.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qna.model.dao.BoardDao;
//import board.model.vo.Attachment;
import qna.model.vo.Board;
import qna.model.vo.Notice;
import qna.model.vo.PageInfo;
import qna.model.vo.Reply;
import qna.model.vo.Search;

public class BoardService {
	private BoardDao bd = new BoardDao();
	
	public Map<String, Object> selectList(int page, Search s) {
		Connection conn = getConnection();
		
		// 1. 게시글 총 개수 구하기
		int listCount = bd.getListCount(conn, s);

		
		// 2. PageInfo 객체 만들기
		 PageInfo pi = new PageInfo(page, listCount, 10, 10);
	
		// 3. 페이징 처리가 된 게시글 목록 조회
		 List<Board> boardList = bd.selectList(conn, pi, s);

		 

		 Map<String, Object> returnMap = new HashMap<>();
		 
		 returnMap.put("pi", pi);
		 returnMap.put("boardList", boardList);
		 
		return returnMap;
	}

	// 게시글 등록
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		
		int result = bd.insertBoard(conn, b);
		
		
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 조회수 증가
	public int increaseCount(int bid) {
		Connection conn = getConnection();
		
		int result = bd.increaseCount(conn, bid);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

//	// 게시글 1개 조회
//	public Board selectBoard(int bid) {
//		Connection conn = getConnection();
//		
//		// 게시글 조회수
//		int result = bd.increaseCount(conn, bid);
//		
//		
//		Board b = null;
//		if(result > 0) {
//			b = bd.selectBoard(conn, bid);
//			// 해당 게시글에 대한 댓글 리스트 조회 로직 추가
//			b.setReplyList(bd.selectReplyList(conn, bid));
//			commit(conn);
//		} else {
//			rollback(conn);
//		}
//		
//		
//		close(conn);
//		
//		return b;
//	}
	
	// 게시글 1개 조회
	public Board selectBoard(int bid) {
		Connection conn = getConnection();
		
		Board b = bd.selectBoard(conn, bid);
		// 해당 게시글에 대한 댓글 리스트 조회 로직 추가
		b.setReplyList(bd.selectReplyList(conn, bid));
		
		close(conn);
		
		return b;
	}

	// 게시글 수정
	public int updateBoard(Board b) {
		Connection conn = getConnection();
		
		int result = bd.updateBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(int bid) {
		Connection conn = getConnection();
		
		int result = bd.deleteBoard(conn, bid);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 게시판 목록 조회
	public List<Board> selectBoardList() {
		Connection conn = getConnection();
		
		List<Board> boardList = bd.selectBoardList(conn);
		
		close(conn);
		
		return boardList;
	}
	
	// 댓글 추가 + 새로 생신 된 댓글 리스트 조회
	public List<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = bd.insertReply(conn, r);
		
		List<Reply> replyList = null;
		
		if(result > 0) {
			commit(conn);
			replyList = bd.selectReplyList(conn, r.getBoard_no());
		} else {
			rollback(conn);
		}
		
		return replyList;
	}

	// 댓글 수정
	public List<Reply> updateReply(Reply r) {
		Connection conn = getConnection();
		
		int result = bd.updateReply(conn, r);
		
		List<Reply> replyList = null;
		
		if(result > 0) {
			commit(conn);
			replyList = bd.selectReplyList(conn, r.getBoard_no());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return replyList;
	}

	// 댓글 삭제
	public int deleteReply(int reply_no) {
		Connection conn = getConnection();
		
		int result = bd.deleteReply(conn, reply_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
