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
		System.out.println("listCount :  " + listCount);
		
		// 2. PageInfo 객체 만들기
		 PageInfo pi = new PageInfo(page, listCount, 10, 10);
		// System.out.println("pi : " + pi);
	
		// 3. 페이징 처리가 된 게시글 목록 조회
		 List<Board> boardList = bd.selectList(conn, pi, s);
		 // System.out.println("boardList : " + boardList);
		 
		/* 값은 중복 저장될 수 있지만 키는 중복 저장될 수 없습니다. 만약 기존에 저장된 키와 동일한 키로 값을 저장하면 기존의 값은 없어지고
		 * 새로운 값으로 대치됩니다. HashMap은 이름 그대로 해싱(Hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는 데 있어서
		 * 뛰어난 성능을 보입니다.*/
		// 리턴용  Map 선언 , jsp 위임할때 가져가기위해(pi, boardList) Map으로 담아서 선언
		 Map<String, Object> returnMap = new HashMap<>();
		 
		 returnMap.put("pi", pi);
		 returnMap.put("boardList", boardList);
		 
		return returnMap;
	}

	// 게시글 등록
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		
		int result = bd.insertBoard(conn, b);
		
		System.out.println("b : " + b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 조회수 증가
//	public int increaseCount(int bid) {
//		Connection conn = getConnection();
//		
//		int result = bd.increaseCount(conn, bid);
//		
//		if(result > 0) {
//			commit(conn);
//		} else {
//			rollback(conn);
//		}
//		
//		close(conn);
//		
//		return result;
//	}

	// 게시글 1개 조회
	public Board selectBoard(int bid) {
		Connection conn = getConnection();
		
		// 게시글 조회수
		int result = bd.increaseCount(conn, bid);
		
		// 해당 게시글에 대한 댓글 리스트 조회 로직 추가
		//b.setReplyList(bd.selectReplyList(conn, bid));
		
		Board b = null;
		if(result > 0) {
			b = bd.selectBoard(conn, bid);
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
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

}
