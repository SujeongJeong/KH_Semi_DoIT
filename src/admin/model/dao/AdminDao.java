package admin.model.dao;

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

import qna.model.vo.PageInfo;
import shop.model.vo.Refund;


public class AdminDao {
	private Properties query = new Properties();
	
	public AdminDao() {
		String fileName = AdminDao.class.getResource("/sql/admin/admin-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 환불 신청 개수 가져오기
		public int getRefundCount(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int RefundCount = 0;
			String sql = query.getProperty("getRefundCount");
				
			try {
				pstmt = conn.prepareStatement(sql);
					
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
		
		public List<Refund> selectRefundList(Connection conn, PageInfo pi) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Refund> refundList = new ArrayList<>();
			String sql = query.getProperty("selectRefundList");
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			try {
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(paramIndex++, startRow);
				pstmt.setInt(paramIndex++, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					refundList.add(new Refund(rset.getInt("REFUND_NO"),
											rset.getTimestamp("REFUND_DATE"),
											rset.getInt("REFUND_COIN"),
											rset.getString("BANK_ACCOUNT"),
											rset.getString("BANK_NAME"),
											rset.getString("ACCOUNT_NAME"),
											rset.getString("USER_EMAIL"),
											rset.getInt("USER_NO")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return refundList;
		}
		
	public int modifyCompleteDate(Connection conn, int refundNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("modifyCompleteDate");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, refundNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	public int modifyUserCoin(Connection conn, int refundNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("modifyUserCoin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, refundNo);
			pstmt.setInt(2, refundNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int selectUserCoin(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int userCoin = 0;
		String sql = query.getProperty("selectUserCoin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			userCoin = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return userCoin;
	}

}
