package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.dao.AdminDao;
import qna.model.vo.PageInfo;
import shop.model.vo.Refund;

public class AdminService {
	AdminDao ad = new AdminDao();

	// 환불 신청 내역 가져오기
	public Map<String, Object> selectRefundList(int page) {
		Connection conn = getConnection();
		
		int RefundCount = ad.getRefundCount(conn);
		
		PageInfo pi = new PageInfo(page, RefundCount, 10, 10);
		
		List<Refund> refundList = ad.selectRefundList(conn, pi);
	
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("refundList", refundList);
		 
		return returnMap;
	}
	
	public int modifyCompleteDate(int refundNo) {
		Connection conn = getConnection();
		
		int result = ad.modifyCompleteDate(conn, refundNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int modifyUserCoin(int refundNo) {
		Connection conn = getConnection();
		
		int result = ad.modifyUserCoin(conn, refundNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
