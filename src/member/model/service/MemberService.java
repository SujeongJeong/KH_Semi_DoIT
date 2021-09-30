package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDao md = new MemberDao();
	// 1. 로그인 기능
	public Member loginMember(String email, String pwd) {
		// JDBCTemplate 만들기
		Connection conn = getConnection();
		// System.out.println(conn);
		
		// MemberDao 클래스 만들어 loginMember 메소드 작성
		Member loginUser = md.loginMember(conn, email, pwd);
		
		close(conn);
		
		return loginUser;
	}
	
	// 2. 회원 가입 기능
	public  int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = md.insertMember(conn, m);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	// 4. 비밀번호 수정 가능
	public Member updatePwd(int userNo, String userPwd, String newPwd) {
		Connection conn = getConnection();
		Member updateMember = null;
		
		int result = md.updatePwd(conn, userNo, userPwd, newPwd);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMember;
	}

	// 5. 회원 탈퇴 기능
	public int deleteMember(int userNo, String userPwd) {
		Connection conn = getConnection();
		
		int result = md.deleteMember(conn, userNo, userPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 아이디 중복체크
	public int emailCheck(String userEmail) {
		Connection conn = getConnection();
		
		int result = md.emailCheck(conn, userEmail);
		
		close(conn);
	
		return result;
	}

	// 닉네임 중복체크
	public int nickCheck(String nickname) {
		Connection conn = getConnection();
		
		int result = md.nickCheck(conn, nickname);
		
		close(conn);
		
		return result;
	}

	// 임시 비밀번호 설정
	public int rsetPwd(String userEmail, String encPwd) {
		Connection conn = getConnection();
		
		int result = md.rsetPwd(conn, userEmail, encPwd);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	// 유저닉네임 변경
	public Member updateNickName(int userNo, String nickName) {
		Connection conn = getConnection();
		
		Member updateMember = null;
		
		int result = md.updateNickName(conn, userNo, nickName);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMember;
	}

	// 목표 공부시간 변경
	public Member setHour(int userNo, String targetHour) {
		Connection conn = getConnection();

		Member updateMember = null;
		
		int result = md.setHour(conn, userNo, targetHour);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		}
		else
			rollback(conn);
		
		close(conn);
		
		return updateMember;
	}

	// 프로필 이미지 변경
	public Member modifyImg(int userNo, String profile_img) {
		Connection conn = getConnection();

		Member updateMember = null;
		
		int result = md.modifyImg(conn, userNo, profile_img);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		}
		else
			rollback(conn);
		
		close(conn);
		
		return updateMember;
	}
	
	// userNo로 회원 한명 조회
	public Member selectMember(int userNo) {
		Connection conn = getConnection();
		
		Member member = md.selectMember(conn, userNo);
		
		return member;
	}
	
	// 스터디방에 가입된 회원 목록 조회
	   public List<Member> memberInfoForStudy(int s_no, int createRoomUser_no) {
	      Connection conn = getConnection();
	      
	      List<Member> memberInfoForStudyList = md.memberInfoForStudy(conn,s_no,createRoomUser_no);
	      
	      close(conn);
	      
	      return memberInfoForStudyList;
	   }
	   
	   

}
