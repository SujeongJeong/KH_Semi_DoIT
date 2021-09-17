package member.model.vo;

import java.util.Date;

public class Member {
	private int userNo;
	private String userEmail;
	private String userPwd;
	private String nickName;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	private String userType;
	private String profileImg;
	private String targetHour;
	private int userCoin;
	private int reportCount;
	
	public Member() {}

	public Member(int userNo, String userEmail, String userPwd, String nickName, Date enrollDate, Date modifyDate, String status,
			String userType, String profileImg, String targetHour, int userCoin, int reportCount) {
		super();
		this.userNo = userNo;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.nickName = nickName;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.userType = userType;
		this.profileImg = profileImg;
		this.targetHour = targetHour;
		this.userCoin = userCoin;
		this.reportCount = reportCount;
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

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getTargetHour() {
		return targetHour;
	}

	public void setTargetHour(String targetHour) {
		this.targetHour = targetHour;
	}

	public int getUserCoin() {
		return userCoin;
	}

	public void setUserCoin(int userCoin) {
		this.userCoin = userCoin;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userEmail=" + userEmail + ", userPwd=" + userPwd + ", nickName="
				+ nickName + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status=" + status
				+ ", userType=" + userType + ", profileImg=" + profileImg + ", targetHour=" + targetHour + ", userCoin="
				+ userCoin + ", reportCount=" + reportCount + "]";
	}
}
