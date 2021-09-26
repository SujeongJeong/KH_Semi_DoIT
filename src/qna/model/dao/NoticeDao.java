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

public class NoticeDao {
	private Properties query = new Properties();
	
	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/board/notice-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
//		System.out.println(noticeList);
		return noticeList;
	}
	
	// 2. 공지사항 글 작성
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNotice_title());
			pstmt.setString(2, n.getNotice_content());
			pstmt.setInt(3, n.getUser_no());
			
			System.out.println(n.getNotice_title() + n.getNotice_content()+n.getUser_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	// 3. 공지사항 조회 시 조회수 증가
	public int increaseCount(Connection conn, int notice_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 4. 공지사항 1개 조회
	public Notice selectNotice(Connection conn, int notice_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		String sql = query.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("notice_no"),
							  rset.getString("notice_title"),
							  rset.getString("notice_content"),
							  rset.getInt("count"),
							  rset.getTimestamp("create_date"),
							  rset.getTimestamp("modify_date"),
							  rset.getString("status"),
							  rset.getInt("user_no"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	// 공지사항 수정
	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateNotice");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNotice_title());
			pstmt.setString(2, n.getNotice_content());
			pstmt.setInt(3, n.getNotice_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	// 공지사항 삭제
	public int deleteNotice(Connection conn, int notice_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 공지사항 당 댓글 리스트 조회
		public List<Reply> selectReplyList(Connection conn, int notice_no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Reply> replyList = new ArrayList<>();
			String sql = query.getProperty("selectReplyList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, notice_no);
				
				rset = pstmt.executeQuery();
				System.out.println("rset : " + rset);
				while(rset.next()) {
					replyList.add(new Reply(rset.getInt("reply_no"),
											rset.getString("reply_content"),
											rset.getTimestamp("create_date"),
											rset.getTimestamp("modify_date"),
											rset.getInt("user_no"),
											rset.getString("nickName"),
											rset.getInt("notice_no")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			System.out.println("noticedao : " + replyList);
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
				pstmt.setInt(2, r.getUser_no());
				pstmt.setInt(3, r.getNotice_no());
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
				pstmt.setInt(3, r.getNotice_no());

				
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
