package study.model.service;

import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import study.model.dao.StudyDao;
import study.model.vo.Study;

import static common.JDBCTemplate.*;

public class StudyService {
	private StudyDao sd = new StudyDao();

	// 스터디방 리스트 조회
	public List<Study> selectStudyList() {
		Connection conn = getConnection();
		
		List<Study> StudyList = sd.selectStudyList(conn);
		
		close(conn);
		
		return StudyList;
	}

	// 스터디방 생성
	public int insertStudyRoom(Study s) {
		Connection conn = getConnection();
		
		// Study 테이블 insert
		int result1 = sd.insertStudy(conn,s);
		// Attachment 테이블 insert
		int result2 = sd.insertStudyPhoto(conn,s.getsImgList());
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1 > 0 && result2 > 0 ? 1 : 0;
	}

	// 스터디방 1개 조회
	public Study selectStudyRoom(int s_no) {
		Connection conn = getConnection();
		
		Study s = sd.selectStudyRoom(conn, s_no);
		
		close(conn);
		
		
		return s;
	}
	
	
}











