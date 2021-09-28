package study.model.vo;

import java.util.Date;

public class MemberTimer {
	/*S_DAY	DATE	No	"sysdate	"	1	날짜
		USER_NO	NUMBER	No		2	회원번호
		S_NO	NUMBER	No		3	스터디방 번호
		S_TIME	NUMBER	No		4	총 공부시간
	 * 
	 * */
	private Date studyDate;
	private int userNo;
	private int studyNo;
	private int studyTime;
	private String studyTimeStr; // 마이페이지에 공부 기록 포맷팅해서 넘기기 위한 변수
	
	public MemberTimer(Date studyDate, int studyTime) {
		super();
		this.studyDate = studyDate;
		this.studyTime = studyTime;
	}
	
	public MemberTimer(Date studyDate, int studyTime, String studyTimeStr) {
		super();
		this.studyDate = studyDate;
		this.studyTime = studyTime;
		this.studyTimeStr = studyTimeStr;
	}

	public Date getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}

	public int getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(int studyTime) {
		this.studyTime = studyTime;
	}
	
	public String getStudyTimeStr() {
		return studyTimeStr;
	}

	public void setStudyTimeStr(String studyTimeStr) {
		this.studyTimeStr = studyTimeStr;
	}

	@Override
	public String toString() {
		return "MemberTimer [studyDate=" + studyDate + ", userNo=" + userNo + ", studyNo=" + studyNo + ", studyTime="
				+ studyTime + ", studyTimeStr=" + studyTimeStr + "]";
	}
	
}
