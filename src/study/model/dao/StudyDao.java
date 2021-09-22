package study.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.Attachment;
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
										rset.getString("file_path")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			
		}
		
		
		return StudyList;
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
			pstmt.setString(4, s.getS_explain());
			pstmt.setString(5, s.getS_notice());
			pstmt.setInt(6, s.getUser_no());
			pstmt.setInt(7, s.getCid());
			
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
					s.setS_no(s_no);
					s.setS_name(rset.getString("s_name"));
					s.setS_to(rset.getInt("s_to"));
					s.setS_day(rset.getString("s_day"));
					// date,time 저장하기
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
			
			if(rset.next()) {
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

	

}









