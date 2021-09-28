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

import qna.model.vo.Board;
import member.model.vo.Member;
import qna.model.vo.PageInfo;
import qna.model.vo.Report;
import qna.model.vo.Search;
import shop.model.vo.Refund;
import study.model.vo.Study;

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

			if (rset.next()) {
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

			while (rset.next()) {
				refundList.add(new Refund(rset.getInt("REFUND_NO"), rset.getTimestamp("REFUND_DATE"),
						rset.getInt("REFUND_COIN"), rset.getString("BANK_ACCOUNT"), rset.getString("BANK_NAME"),
						rset.getString("ACCOUNT_NAME"), rset.getString("USER_EMAIL"), rset.getInt("USER_NO")));
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

	// DO-IT 회원 개수 가져오기
	public int getMemberCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int MemberCount = 0;
		String sql = query.getProperty("getMemberCount");

		if (s.getSearchValue() != null) {
			sql = query.getProperty("getNicknameListCount");
		}
		try {
			pstmt = conn.prepareStatement(sql);

			if (s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
				MemberCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MemberCount;
	}

	// 페이징 처리 된 memberList 조회
	public List<Member> selectMemberList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<>();
		String sql = query.getProperty("selectMemberList");

		// 검색 조건과 검색 값이 넘어왔을 경우
		if (s.getSearchValue() != null) {
			sql = query.getProperty("selectMemberNicknameList");
		}

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			// 검색 조건과 검색 값이 넘어온 경우
			if (s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				memberList.add(
						new Member(rset.getInt("USER_NO"), rset.getString("USER_EMAIL"), rset.getString("USER_PWD"),
								rset.getString("NICKNAME"), rset.getDate("ENROLL_DATE"), rset.getDate("MODIFY_DATE"),
								rset.getString("STATUS"), rset.getString("USER_TYPE"), rset.getString("PROFILE_IMG"),
								rset.getString("TARGET_HOUR"), rset.getInt("USER_COIN"), rset.getInt("REPORT_COUNT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberList;
	}

	// 회원 삭제
	public int memberDelete(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteMember");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	// 탈퇴된 회원의 게시판 삭제
	public void memberBoardDelete(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("memberBoardDelete");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
	}

	// 탈퇴된 회원의 공지사항삭제
	public void memberNoticeDelete(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("memberNoticeDelete");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
	}

	// 삭제된 회원의 댓글 삭제
	public void memberReplyDelete(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("memberReplyDelete");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
	}

	public int getMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int MemberCount = 0;
		String sql = query.getProperty("getMemberCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				MemberCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return MemberCount;
	}

	public List<Member> selectMemberList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<>();
		String sql = query.getProperty("selectMemberList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				memberList.add(
						new Member(rset.getInt("USER_NO"), rset.getString("USER_EMAIL"), rset.getString("USER_PWD"),
								rset.getString("NICKNAME"), rset.getDate("ENROLL_DATE"), rset.getDate("MODIFY_DATE"),
								rset.getString("STATUS"), rset.getString("USER_TYPE"), rset.getString("PROFILE_IMG"),
								rset.getString("TARGET_HOUR"), rset.getInt("USER_COIN"), rset.getInt("REPORT_COUNT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberList;
	}

	// 회원 관리자 권환 부여
	public int adminGrant(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("adminGrant");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 회원 관리자 권한 박탈
	public int adminDisqualify(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("adminDisqualify");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 스터디 개수 가져오기
	public int getStudyCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int StudyCount = 0;
		String sql = query.getProperty("getStudyCount");

		if (s.getSearchValue() != null) {
			sql = query.getProperty("getStudynameListCount");
		}
		try {
			pstmt = conn.prepareStatement(sql);

			if (s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
				StudyCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyCount;
	}

	// 페이징 처리 된 studyList 조회
	public List<Study> selectStudyList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> studyList = new ArrayList<>();
		String sql = query.getProperty("selectStudyList");

		// 검색 조건과 검색 값이 넘어왔을 경우
		if (s.getSearchValue() != null) {
			sql = query.getProperty("selectStudyNameList");
		}

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			// 검색 조건과 검색 값이 넘어온 경우
			if (s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				studyList.add(new Study(rset.getInt("s_no"), rset.getString("s_name"), rset.getInt("s_to"),
						rset.getString("s_day"), rset.getDate("s_startPeriod"), rset.getDate("s_endPeriod"),
						rset.getDate("s_startTime"), rset.getDate("s_endTime"), rset.getString("s_explain"),
						rset.getString("s_notice"), rset.getString("cname"), rset.getString("nickname"),
						rset.getString("change_name"), rset.getString("file_path"), rset.getInt("studyMemberNum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return studyList;
	}

	// 스터디 삭제
	public int studyDelete(Connection conn, int studyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteStudy");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, studyNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int getStudyCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int StudyCount = 0;
		String sql = query.getProperty("getStudyCount");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				StudyCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyCount;
	}

	public List<Study> selectStudyList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> studyList = new ArrayList<>();
		String sql = query.getProperty("selectStudyList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				studyList.add(new Study(rset.getInt("s_no"), rset.getString("s_name"), rset.getInt("s_to"),
						rset.getString("s_day"), rset.getDate("s_startPeriod"), rset.getDate("s_endPeriod"),
						rset.getDate("s_startTime"), rset.getDate("s_endTime"), rset.getString("s_explain"),
						rset.getString("s_notice"), rset.getString("cname"), rset.getString("nickname"),
						rset.getString("change_name"), rset.getString("file_path"), rset.getInt("studyMemberNum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return studyList;
	}

	// 신고 처리된 count
	public int getReportMemberListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getAllListCount");

		if (s.getCategory() != null && s.getRange() != null) {
			if (s.getCategory().equals("all")) {
				sql = query.getProperty("getAllListCount");
			} else if (s.getCategory().equals("board")) {
				sql = query.getProperty("getBoardListCount");
			} else if (s.getCategory().equals("reply")) {
				sql = query.getProperty("getReplyListCount");
			} else if (s.getCategory().equals("study-member")) {
				sql = query.getProperty("getStudyListCount");
			}
		}
		try {
			pstmt = conn.prepareStatement(sql);

//			if(s.getSearchValue() != null) {
//				pstmt.setString(1, s.getSearchValue());
//			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
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


	// member 신고 list
	/*
	 * public List<Report> selectReportMemberList(Connection conn, PageInfo pi,
	 * Search s) { PreparedStatement pstmt = null; ResultSet rset = null;
	 * List<Board>boardList = new ArrayList<>(); String sql =
	 * query.getProperty("selectList");
	 * 
	 * // 검색 조건과 검색 값이 넘어왔을 경우
	 * 
	 * if(s.getSearchCondition() != null && s.getSearchValue() != null) {
	 * if(s.getSearchCondition().equals("title")) { // 재목 검색 sql =
	 * query.getProperty("selectTitleList"); } else
	 * if(s.getSearchCondition().equals("content")) { // 내용 검색 sql =
	 * query.getProperty("selectContentList"); } else
	 * if(s.getSearchCondition().contentEquals("writer")) { // 작성자 검색 sql =
	 * query.getProperty("selectWriterList"); } }
	 * 
	 * 
	 * if(s.getCategory() != null && s.getRange() != null) {
	 * if(s.getCategory().equals("all") && s.getRange().equals("latest")) { sql =
	 * query.getProperty("getAllLatestListCount"); } else
	 * if(s.getCategory().equals("all") && s.getRange().equals("report-count")) {
	 * sql = query.getProperty("getAllLatestListCount"); } else
	 * if(s.getCategory().equals("all") &&
	 * s.getRange().equals("memberReport-count")) { sql =
	 * query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("board") && s.getRange().equals("latest")) { sql =
	 * query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("board") && s.getRange().equals("report-count")) {
	 * sql = query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("board") &&
	 * s.getRange().equals("memberReport-count")) { sql =
	 * query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("reply") && s.getRange().equals("latest")) { sql =
	 * query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("reply") && s.getRange().equals("report-count")) {
	 * sql = query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("reply") &&
	 * s.getRange().equals("memberReport-count")) { sql =
	 * query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("study-member") && s.getRange().equals("latest")) {
	 * sql = query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("study-member") &&
	 * s.getRange().equals("report-count")) { sql =
	 * query.getProperty("getReportMemberListCount"); } else
	 * if(s.getCategory().equals("study-member") &&
	 * s.getRange().equals("memberReport-count")) { sql =
	 * query.getProperty("getReportMemberListCount"); }
	 * 
	 * 
	 * try { pstmt = conn.prepareStatement(sql);
	 * 
	 * int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1; int endRow =
	 * startRow + pi.getBoardLimit() - 1; int paramIndex = 1;
	 * 
	 * // 검색 조건과 검색 값이 넘어온 경우 if(s.getSearchCondition() != null &&
	 * s.getSearchValue() != null) { pstmt.setString(paramIndex++,
	 * s.getSearchValue()); }
	 * 
	 * pstmt.setInt(paramIndex++, startRow); pstmt.setInt(paramIndex++, endRow);
	 * 
	 * rset = pstmt.executeQuery();
	 * 
	 * while(rset.next()) { boardList.add(new Board(rset.getInt("bid"),
	 * rset.getString("cname"), rset.getString("btitle"),
	 * rset.getString("user_name"), rset.getInt("bcount"),
	 * rset.getDate("create_date"))); }
	 * 
	 * // System.out.println("dao" + boardList); } catch (SQLException e) {
	 * e.printStackTrace(); } finally { close(rset); close(pstmt); } return
	 * boardList; }
	 */
}
