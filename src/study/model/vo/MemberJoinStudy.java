package study.model.vo;

public class MemberJoinStudy {
	private int userNo;		// 	클릭한 스터디방의 스터디방 번호
	private int s_no;		//	로그인되어있는 회원의 회원번호
/*	
	USER_NO	NUMBER
	S_NO	NUMBER
*/	
	public MemberJoinStudy() {}
	
	public MemberJoinStudy(int userNo, int s_no) {
		super();
		this.userNo = userNo;
		this.s_no = s_no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	@Override
	public String toString() {
		return "MemberJoinStudy [userNo=" + userNo + ", s_no=" + s_no + "]";
	}
	
	
}
