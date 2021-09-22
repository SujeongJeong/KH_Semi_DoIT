package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

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
	
//	// 3. 회원 정보 수정 기능
//	public Member updateMember(Member m) {
//	Connection conn = getConnection();
//	Member updateMember = null;
//	
//	// 1. 수정하려는 정보가 담긴 Member 객체를 가지고 Dao에서 updateMember 수행
//	int result = md.updateMember(conn, m);
//
//	// 2. 수정이 잘 되었다면 수정 된 정보의 member 객체 select 후 리턴
//	if(result > 0) {
//		updateMember = md.selectMember(conn, m.getUserNo());
//		commit(conn);
//	} else {
//		rollback(conn);
//	}
//	 
//	return updateMember;
//	}
//	
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
}
