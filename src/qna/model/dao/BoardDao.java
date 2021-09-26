package qna.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import qna.model.vo.Attachment;
import qna.model.vo.Board;
import qna.model.vo.Notice;
import qna.model.vo.PageInfo;
import qna.model.vo.Reply;
import qna.model.vo.Search;

public class BoardDao {
	private Properties query = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 게시글 총 개수 구하기 (select 구문 실행)
	public int getListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount");
		
		if(s.getSearchValue() != null) {
			sql = query.getProperty("getTitleListCount");
		}
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				// count(*) 이라는 함수를 사용한거기 때문에 별칭이 있어야 가져올 수 있기 때문에 1번째 index를 가져온다고 해서 가져왔다
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	// 페이징 처리 된 boardList 조회
	public List<Board> selectList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board>boardList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		// 검색 조건과 검색 값이 넘어왔을 경우
		if(s.getSearchValue() != null) {
				sql = query.getProperty("selectTitleList");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			// 검색 조건과 검색 값이 넘어온 경우
			if(s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardList.add(new Board(rset.getInt("board_no"),
										rset.getString("cname"),
										rset.getString("board_title"),
										rset.getString("nickname"),
										rset.getDate("create_date"),
										rset.getInt("count")));
			}
			
			// System.out.println("dao" + boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardList;
	}

	// 게시글 등록
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b.getCid());
			pstmt.setString(2, b.getBoard_title());
			pstmt.setString(3, b.getBoard_content());
			pstmt.setInt(4, b.getUser_no());
			
			result = pstmt.executeUpdate();
			
			// System.out.println("result : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 조회수 증가
	public int increaseCount(Connection conn, int bid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
	
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	// 게시글 1개 조회
	public Board selectBoard(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String sql = query.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("board_no"),
							  rset.getInt("cid"),
							  rset.getString("cname"),
							  rset.getString("board_title"),
							  rset.getString("nickname"),
							  rset.getString("board_content"),
							  rset.getTimestamp("create_date"),
							  rset.getTimestamp("modify_date"),
							  rset.getInt("user_no"),
							  rset.getInt("count"));
				
				// getDate -> 년/월/일 데이터만 사용 가능하고 시/분/초 데이터 사용 불가능
				// getTimestamp -> 년/월/일/시/분/초 데이터 사용 가능
				// Timestamp는 Date 클래스의 후손
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	// 게시글 수정
	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, b.getCid());
			pstmt.setString(2, b.getBoard_title());
			pstmt.setString(3, b.getBoard_content());
			pstmt.setInt(4, b.getBoard_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시판 목록 조회
	public List<Board> selectBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = query.getProperty("selectBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardList.add(new Board(rset.getInt("board_no"),
										rset.getString("cname"),
										rset.getString("board_title"),
										rset.getString("nickname"),
										rset.getString("board_content"),
										rset.getDate("create_date"),
										rset.getDate("modify_date"),
										rset.getString("status"),
										rset.getString("board_secret"),
										rset.getInt("count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	    return boardList;
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
						
				while(rset.next()) {
					noticeList.add(new Notice(rset.getInt("notice_no"),
											  rset.getString("notice_title"),
											  rset.getString("notice_content"),
											  rset.getInt("count"),
											  rset.getDate("create_date"),
											  rset.getDate("modify_date"),
											  rset.getString("status"),
											  rset.getInt("user_no")));

				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			// System.out.println(noticeList);
			return noticeList;
		}
	
	// 게시글 당 댓글 리스트 조회
	public List<Reply> selectReplyList(Connection conn, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> replyList = new ArrayList<>();
		String sql = query.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				replyList.add(new Reply(rset.getInt("reply_no"),
										rset.getString("reply_content"),
										rset.getTimestamp("create_date"),
										rset.getTimestamp("modify_date"),
										rset.getInt("board_no"),
										rset.getInt("user_no"),
										rset.getString("nickName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return replyList;
	}

	// 댓글 추가
	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReply_content());
			pstmt.setInt(2, r.getBoard_no());
			pstmt.setInt(3, r.getUser_no());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 댓글 수정
	public int updateReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReply_content());
			pstmt.setInt(2, r.getReply_no());
			pstmt.setInt(3, r.getBoard_no());

			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 댓글 삭제
	public int deleteReply(Connection conn, int reply_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reply_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
