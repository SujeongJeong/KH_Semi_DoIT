package study.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.Attachment;
import study.model.vo.MemberJoinStudy;
import study.model.vo.Study;
import static common.JDBCTemplate.*;


public class StudyDao {

	private Properties query = new Properties();
	
	public StudyDao() {
		String fileName = StudyDao.class.getResource("/sql/study/study-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 스터디방 리스트 조회
	public List<Study> selectStudyList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> StudyList = new ArrayList<>();
		String sql = query.getProperty("selectStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudyList.add(new Study(rset.getInt("s_no"),
										rset.getString("s_name"),
										rset.getInt("s_to"),
										rset.getString("s_day"),
										rset.getTimestamp("s_startPeriod"),
										rset.getTimestamp("s_endPeriod"),
										rset.getTimestamp("s_startTime"),
										rset.getTimestamp("s_endTime"),
										rset.getString("s_explain"),
										rset.getString("s_notice"),
										rset.getString("cname"),
										rset.getString("nickname"),
										rset.getString("change_name"),
										rset.getString("file_path"),
										rset.getInt("studyMemberNum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyList;
	}
	
	// 스터디방별 가입된 회원수 조회
	public List<Study> selectStudyMemberList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		List<Study> StudyMemberList = new ArrayList<>();
		
		String sql = query.getProperty("selectStudyMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				StudyMemberList.add(new Study(rset.getInt("s_no"),
											  rset.getInt("s_memberCount")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyMemberList;
	}
	
	// 스터디방 생성
	public int insertStudy(Connection conn, Study s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertStudyRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getS_name());
			pstmt.setInt(2, s.getS_to());
			pstmt.setString(3, s.getS_day());
			
			pstmt.setTimestamp(4, new Timestamp(s.getS_startPeriod().getTime()));
			pstmt.setTimestamp(5, new Timestamp(s.gets_endPeriod().getTime()));
			pstmt.setTimestamp(6, new Timestamp(s.getS_startTime().getTime()));
			pstmt.setTimestamp(7, new Timestamp(s.gets_endTime().getTime()));
					
//			System.out.println("new Timestamp(s.getS_startTime().getTime())"+new Timestamp(s.getS_startTime().getTime()));
//			System.out.println("new Timestamp(s.getS_startTime().getHours())"+new Timestamp(s.getS_startTime().getHours()));
//			System.out.println("new Timestamp(s.getS_startTime().getMinutes())"+new Timestamp(s.getS_startTime().getMinutes()));
//			System.out.println("new Timestamp(s.getS_startTime().getHours()+s.getS_startTime().getMinutes())"+new Timestamp(s.getS_startTime().getHours()+s.getS_startTime().getMinutes()));
			
			pstmt.setString(8, s.getS_explain());
			pstmt.setString(9, s.getS_notice());
			pstmt.setInt(10, s.getUser_no());
			pstmt.setInt(11, s.getCid());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	// 스터디방 첨부이미지 attachment테이블에 insert
	public int insertStudyPhoto(Connection conn, List<Attachment> getsImgList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertStudyPhoto");
		
		try {
			Attachment at = getsImgList.get(0);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOrigin_name());
			pstmt.setString(2, at.getChange_name());
			pstmt.setString(3, at.getFile_path());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 스터디방 1개 조회(s_no)이용
	public Study selectStudyRoom(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Study s = null;
		int count=0;
		String sql = query.getProperty("selectStudyRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);
			
			rset=pstmt.executeQuery();
			
			while (rset.next()) {
				if (count++ < 1) {
					//스터디방 정보는 첫번째 반복에서만 처리(중복)
					s = new Study();
					s.setS_no(rset.getInt("s_no"));
					s.setS_name(rset.getString("s_name"));
					s.setS_to(rset.getInt("s_to"));
					s.setS_day(rset.getString("s_day"));
					s.setS_startPeriod(rset.getTimestamp("s_startPeriod"));
					s.sets_endPeriod(rset.getTimestamp("s_endPeriod"));
					s.setS_startTime(rset.getTimestamp("s_startTime"));
					s.sets_endTime(rset.getTimestamp("s_endTime"));
					s.setS_explain(rset.getString("s_explain"));
					s.setS_notice(rset.getString("s_notice"));
					s.setCname(rset.getString("cname"));
					s.setUser_nkname(rset.getString("nickname"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return s;
	}
	// 스터디방 삭제
	public int deleteStudy(Connection conn, int s_no) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql=query.getProperty("deleteStudy");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 스터디방 총 갯수 구하기
	public int selectStudyListNumber(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int studyListCount = 0;
		
		String sql = query.getProperty("selectStudyListNumber");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				studyListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return studyListCount;
	}

	// 스터디방 가입
	public int insertMemberJoinStudy(Connection conn, MemberJoinStudy mjs) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = query.getProperty("insertMemberJoinStudy");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mjs.getUserNo());
			pstmt.setInt(2, mjs.getS_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 스터디방 가입 여부 조회
	public MemberJoinStudy selectMemberJoinStudy(Connection conn, int userNo, int s_no) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql = query.getProperty("selectMemberJoinStudy");
		MemberJoinStudy mjs= null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, s_no);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				mjs = new MemberJoinStudy();
				
				mjs.setUserNo(userNo);
				mjs.setS_no(s_no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mjs;
	}

	// 멤버별 가입된 스터디방의 수
	public int memberJoinStudyNum(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int result=0;
		
		String sql = query.getProperty("memberJoinStudyNum");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);

			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// 스터디방별 가입된 회원수
	public int StudyMemberCount(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int result=0;
		
		String sql = query.getProperty("StudyMemberCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);

			rset=pstmt.executeQuery();
			if (rset != null) {
				while (rset.next()) {
					result = rset.getInt(1);
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// 회원별 가입한 스터디방 제목, 인원수, 정원, 카테고리 조회
    public List<Study> selectMyStudy(Connection conn, int userNo) {
       PreparedStatement pstmt = null;
       ResultSet rset = null;
       List<Study> resultList = new ArrayList<>();
       String sql = query.getProperty("selectMyStudy");
       
       try {
          pstmt = conn.prepareStatement(sql);
          
          pstmt.setInt(1, userNo);
          
          rset = pstmt.executeQuery();
          while(rset.next()) {
             resultList.add(new Study(rset.getInt("s_no"),
                                rset.getString("s_name"),
                                rset.getInt("s_to"),
                                rset.getString("cname"),
                                rset.getString("file_path"),
                                rset.getString("change_name")));
          }
          
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
          close(rset);
          close(pstmt);
       }
       return resultList;
    }

    // 유저번호로 만든 스터디방 가져오기(가장최근에 만든 1개)
	public Study selectStudyRoomOnlySNo(Connection conn, int user_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Study s = null;
		int count = 0;
		
		String sql = query.getProperty("selectStudyRoomOnlySNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, user_no);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				if(count++ < 1) {
					s = new Study();
					s.setS_no(rset.getInt("s_no"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return s;
	}



	

}









