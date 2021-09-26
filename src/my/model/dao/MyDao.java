package my.model.dao;

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

import qna.model.vo.Board;
import qna.model.vo.PageInfo;
import shop.model.vo.Charge;
import shop.model.vo.Purchase;
import shop.model.vo.Refund;
import study.model.vo.Study;

public class MyDao {
	private Properties query = new Properties();
	
	public MyDao() {
		String fileName = MyDao.class.getResource("/sql/my/my-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	// 게시글 총 개수
	public int getListCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
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
	// 댓글 단 게시글 총 개수
	public int getReplyCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int ReplyListCount = 0;
		String sql = query.getProperty("getReplyListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ReplyListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return ReplyListCount;
	}
	
	// 게시글 리스트 
	public List<Board> selectMyBoardList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> MyboardList = new ArrayList<>();
		String sql = query.getProperty("selectMyBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 2;
			
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyboardList.add(new Board(rset.getInt("board_no"),
										rset.getString("cname"),
										rset.getString("board_title"),
										rset.getString("nickname"),
										rset.getDate("create_date"),
										rset.getInt("count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyboardList;
	}
	
	// 댓글을 단 게시글 리스트
	public List<Board> selectMyReplyList(Connection conn, PageInfo pi2, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> MyReplyList = new ArrayList<>();
		String sql = query.getProperty("selectMyReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi2.getPage() - 1) * pi2.getBoardLimit() + 1;
			int endRow = startRow + pi2.getBoardLimit() - 1;
			int paramIndex = 2;
			
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyReplyList.add(new Board(rset.getInt("board_no"),
										rset.getString("cname"),
										rset.getString("board_title"),
										rset.getString("nickname"),
										rset.getDate("create_date"),
										rset.getInt("count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyReplyList;
	}
	
	// 개설한 스터디 개수 가져오기
	public int getOpenStudyCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int openStudyCount = 0;
		String sql = query.getProperty("getOpenStudyCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
				openStudyCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return openStudyCount;
	}
	
	// 개설한 스터디 리스트 가져오기
	public List<Study> selectMyOpenStudyList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> MyOpenStudyList = new ArrayList<>();
		String sql = query.getProperty("selectMyOpenStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 2;
			
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyOpenStudyList.add(new Study(rset.getInt("S_NO"),
										rset.getString("CNAME"),
										rset.getString("S_NAME"),
										rset.getInt("S_TO"),
										rset.getString("NICKNAME"),
										rset.getDate("S_STARTPERIOD"),
										rset.getDate("S_ENDPERIOD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyOpenStudyList;
	}

	// 참여한 스터디 리스트 가져오기
	public List<Study> selectMyJoinStudyList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> MyJoinStudyList = new ArrayList<>();
		String sql = query.getProperty("selectMyJoinStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MyJoinStudyList.add(new Study(rset.getInt("S_NO"),
										rset.getString("CNAME"),
										rset.getString("S_NAME"),
										rset.getInt("S_TO"),
										rset.getString("NICKNAME"),
										rset.getDate("S_STARTPERIOD"),
										rset.getDate("S_ENDPERIOD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MyJoinStudyList;
	}
	
	// 개설한 스터디 삭제
	public int deleteOpenStudy(Connection conn, int deleteSNo, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteOpenStudy");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, deleteSNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 참여한 스터디 나가기
	public int exitJoinStudy(Connection conn, int exitSNo, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("exitJoinStudy");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, exitSNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 구매한 아이템 리스트 가져오기
	public List<Purchase> selectItemList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Purchase> ItemList = new ArrayList<>();
		String sql = query.getProperty("selectItemList");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			// 검색 조건과 검색 값이 넘어온 경우
			pstmt.setInt(1, userNo);
		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ItemList.add(new Purchase(rset.getString("PRODUCT_NAME"),
										rset.getDate("START_DATE"),
										rset.getDate("EXPIRATION_DATE"),
										rset.getInt("PRODUCT_PRICE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return ItemList;
	}
	
	// 환불 신청
	public int insertRefundCoin(Connection conn, Refund r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertRefundCoin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getRefundCoin());
			pstmt.setString(2, r.getBankAccount());
			pstmt.setString(3, r.getBankName());
			pstmt.setString(4, r.getAccountName());
			pstmt.setInt(5, r.getUserNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int getPurchaseCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int PurchaseCount = 0;
		String sql = query.getProperty("getPurchaseCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
				PurchaseCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return PurchaseCount;
	}
	
	public int getChargeCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int ChargeCount = 0;
		String sql = query.getProperty("getChargeCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
				ChargeCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return ChargeCount;
	}
	
	public int getRefundCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int RefundCount = 0;
		String sql = query.getProperty("getUserRefundCount");
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
				
			if(rset.next()) {
				RefundCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return RefundCount;
	}
	
	public List<Purchase> selectPurchaseList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Purchase> PurchaseList = new ArrayList<>();
		String sql = query.getProperty("selectPurchaseList");
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		int paramIndex = 2;
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PurchaseList.add(new Purchase(rset.getTimestamp("PURCHASE_DATE"),
												rset.getString("PRODUCT_NAME"),
												rset.getInt("PRODUCT_COUNT"),
												rset.getInt("PRODUCT_PRICE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return PurchaseList;
	}
	
	public List<Charge> selectChargeList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Charge> ChargeList = new ArrayList<>();
		String sql = query.getProperty("selectChargeList");
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		int paramIndex = 2;
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ChargeList.add(new Charge(rset.getTimestamp("CHARGE_DATE"),
											rset.getInt("CHARGE_COIN")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return ChargeList;
	}
	
	public List<Refund> selectUserRefundList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Refund> RefundList = new ArrayList<>();
		String sql = query.getProperty("selectUserRefundList");
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		int paramIndex = 2;
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				RefundList.add(new Refund(rset.getTimestamp("REFUND_DATE"),
											 rset.getInt("REFUND_COIN"),
											 rset.getTimestamp("COMPLETE_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return RefundList;
	}
	
}
