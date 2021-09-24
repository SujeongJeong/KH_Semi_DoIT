package my.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.model.dao.MyDao;
import qna.model.vo.Board;
import qna.model.vo.PageInfo;
import shop.model.vo.Purchase;
import shop.model.vo.Refund;
import study.model.vo.Study;

public class MyService {
	private MyDao md = new MyDao();

	// 내가 작성한 게시글 가져오기
	public Map<String, Object> selectMyBoardList(int page, int userNo) {
		Connection conn = getConnection();
		
		int listCount = md.getListCount(conn, userNo);
	
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		
		List<Board> MyboardList = md.selectMyBoardList(conn, pi, userNo);
		
		if(!MyboardList.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
		 
		 returnMap.put("pi", pi);
		 returnMap.put("MyboardList", MyboardList);
		 
		return returnMap;
	}

	public Map<String, Object> selectMyReplyList(int page, int userNo) {
		Connection conn = getConnection();
		
		int ReplyListCount = md.getReplyCount(conn, userNo);
	
		PageInfo pi2 = new PageInfo(page, ReplyListCount, 10, 10);
		
		List<Board> MyReplyList = md.selectMyReplyList(conn, pi2, userNo);
		
		if(!MyReplyList.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
		 
		 returnMap.put("pi2", pi2);
		 returnMap.put("MyReplyList", MyReplyList);
		 
		return returnMap;
	}

	public Map<String, Object> selectMyOpenStudyList(int page, int userNo) {
		Connection conn = getConnection();
		
		int openStudyCount = md.getOpenStudyCount(conn, userNo);
	
		PageInfo pi = new PageInfo(page, openStudyCount, 10, 10);
		
		List<Study> MyOpenStudyList = md.selectMyOpenStudyList(conn, pi, userNo);
		
		if(!MyOpenStudyList.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
		 
		 returnMap.put("pi", pi);
		 returnMap.put("MyOpenStudyList", MyOpenStudyList);
		 
		return returnMap;
	}

	public Map<String, Object> selectMyJoinStudyList(int userNo) {
		Connection conn = getConnection();
		
		List<Study> MyJoinStudyList = md.selectMyJoinStudyList(conn, userNo);
		
		if(!MyJoinStudyList.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
	
		 returnMap.put("MyJoinStudyList", MyJoinStudyList);
		 
		return returnMap;
	}

	public int deleteOpenStudy(int deleteSNo, int userNo) {
		Connection conn = getConnection();
		
		int result = md.deleteOpenStudy(conn, deleteSNo, userNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int exitJoinStudy(int exitSNo, int userNo) {
		Connection conn = getConnection();
		
		int result = md.exitJoinStudy(conn, exitSNo, userNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Map<String, Object> selectItemList(int userNo) {
		Connection conn = getConnection();
		
		List<Purchase> ItemList = md.selectItemList(conn, userNo);
		
		if(!ItemList.isEmpty()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		Map<String, Object> returnMap = new HashMap<>();
	
		 returnMap.put("ItemList", ItemList);
		 
		return returnMap;
	}

	public int insertRefundCoin(Refund r) {
		Connection conn = getConnection();
		
		int result = md.insertRefundCoin(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		return result;
	}

}
