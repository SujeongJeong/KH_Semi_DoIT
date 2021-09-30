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
import qna.model.vo.Board;
import member.model.vo.Member;
import qna.model.vo.PageInfo;
import qna.model.vo.Report;
import qna.model.vo.Search;
import shop.model.vo.Refund;
import study.model.vo.Study;

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

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	// Do-it 회원 내역 가져오기
	public Map<String, Object> selectMemberList(int page, Search s) {
		Connection conn = getConnection();

		int MemberCount = ad.getMemberCount(conn, s);

		PageInfo pi = new PageInfo(page, MemberCount, 10, 10);

		List<Member> memberList = ad.selectMemberList(conn, pi, s);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("memberList", memberList);

		return returnMap;
	}

	// 회원 삭제
	public int memberDelete(int userNo) {
		Connection conn = getConnection();

		int result1 = ad.memberDelete(conn, userNo);

		if (result1 > 0) {
			ad.memberBoardDelete(conn, userNo);
			ad.memberNoticeDelete(conn, userNo);
			ad.memberReplyDelete(conn, userNo);
		}

		if (result1 > 0) {

			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result1;
	}

	public Map<String, Object> selectMember(int page) {
		Connection conn = getConnection();

		int MemberCount = ad.getMemberCount(conn);

		PageInfo pi = new PageInfo(page, MemberCount, 10, 10);

		List<Member> memberList = ad.selectMemberList(conn, pi);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("memberList", memberList);

		return returnMap;
	}

	// 회원 관리자 권한 부여
	public int adminGrant(int userNo) {
		Connection conn = getConnection();

		int result = ad.adminGrant(conn, userNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	// 회원 관리자 권한 박탈
	public int adminDisqualify(int userNo) {
		Connection conn = getConnection();

		int result = ad.adminDisqualify(conn, userNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	// 스터디 내역 가져요기
	public Map<String, Object> selectStudyList(int page, Search s) {
		Connection conn = getConnection();

		int StudyCount = ad.getStudyCount(conn, s);

		PageInfo pi = new PageInfo(page, StudyCount, 10, 10);

		List<Study> studyList = ad.selectStudyList(conn, pi, s);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("studyList", studyList);

		return returnMap;
	}

	// 스터디 삭제
	public int studyDelete(int studyNo) {
		Connection conn = getConnection();

		int result = ad.studyDelete(conn, studyNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	public Map<String, Object> selectStudy(int page) {
		Connection conn = getConnection();

		int StudyCount = ad.getStudyCount(conn);

		PageInfo pi = new PageInfo(page, StudyCount, 10, 10);

		List<Study> studyList = ad.selectStudyList(conn, pi);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("studyList", studyList);

		return returnMap;
	}


	// 회원 신고 내역 가져오기
	  public Map<String, Object> selectReportMemberList(int page, Search s) {
	  Connection conn = getConnection();
	  
	  int listCount = ad.getReportMemberListCount(conn, s);
	  
	  
	  PageInfo pi = new PageInfo(page, listCount, 10, 10);
	  
	  
	  List<Report> reportMemberList = ad.selectReportMemberList(conn, pi, s);
	  
	  Map<String, Object> returnMap = new HashMap<>();
	  
	  returnMap.put("pi", pi); 
	  returnMap.put("reportMemberList", reportMemberList);

	  return returnMap; 
	  }

	  // 신고 받은 게시글 리스트 가져오기
	public List<Report> selectBoardReportList(int br_no) {
		Connection conn = getConnection();
		
		List<Report> reportList = ad.boardReportList(conn, br_no);
		
		close(conn);
		
		return reportList;
	}

	// 신고받은 댓글 신고리스트 가져오기
	public List<Report> selectReplyReportList(int br_no) {
		Connection conn = getConnection();
		
		List<Report> reportList = ad.ReplyReportList(conn, br_no);
		
		close(conn);
		
		return reportList;
	}

	// 회원이 받은 모든 신고리스트 가져오기
	public List<Report> selectReportAllList(int ruser_no) {
		Connection conn = getConnection();
		
		List<Report> reportList = ad.ReportAllList(conn, ruser_no);
		
		close(conn);
		
		return reportList;
	}
	 
}
