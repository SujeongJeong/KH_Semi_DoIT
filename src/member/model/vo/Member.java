package member.model.vo;

import java.sql.Date;

public class Member {
	private int userNo;
	private String userEmail;
	private String userPwd;
	private String nickName;
	private String status;
	
	public Member() {}

	public Member(int userNo, String userEmail, String userPwd, String nickName, String status) {
		super();
		this.userNo = userNo;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.nickName = nickName;
		this.status = status;
	}
	
	public Member(int userNo, String userEmail, String userPwd, String nickName) {
		super();
		this.userNo = userNo;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.nickName = nickName;
	}

	public Member(String userEmail, String userPwd, String nickName) {
		super();
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.nickName = nickName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}
