package qna.model.vo;

import java.util.Date;

public class Report {
	private int report_no;					// 신고번호
	private String report_content;			// 신고사유
	private Date report_date;				// 신고날짜
	private int user_no;					// 신고자 회원번호
	// join 값
	private String nick_name;				// 신고자 회원닉네임
	private String nickname;				// 신고당한 회원닉네임
	private int r_user_no;					// 신고당한 회원번호
	private String subject;					// 분류
	private int br_no;						// board_no, reply_no
	private int board_no;					// 게시글 번호,댓글의 게시글번호
	private String board_title;				// 게시글제목
	private int rcount;						// 게시글,댓글의 누적 신고 count
	private int allcount;					// 회원의 누적 신고 count

	
	public Report() {}

	
	
	public Report(int user_no) {
		super();
		this.user_no = user_no;
	} 


	public Report(String report_content, int user_no) {
		super();
		this.report_content = report_content;
		this.user_no = user_no;
	}

	public Report(int report_no, String report_content, Date report_date, int user_no) {
		super();
		this.report_no = report_no;
		this.report_content = report_content;
		this.report_date = report_date;
		this.user_no = user_no;
	}

	// --------------------
	

	
	
	
	
	public Report(int report_no, Date report_date, String nickname, int r_user_no, String subject, int br_no,
			int board_no, int rcount, int allcount) {
		super();
		this.report_no = report_no;
		this.report_date = report_date;
		this.nickname = nickname;
		this.r_user_no = r_user_no;
		this.subject = subject;
		this.br_no = br_no;
		this.board_no = board_no;
		this.rcount = rcount;
		this.allcount = allcount;
	}
	
	
	
	public Report(int report_no, String report_content, Date report_date, int user_no, String nick_name,
			String board_title) {
		super();
		this.report_no = report_no;
		this.report_content = report_content;
		this.report_date = report_date;
		this.user_no = user_no;
		this.nick_name = nick_name;
		this.board_title = board_title;
	}



	public int getReport_no() {
		return report_no;
	}



	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
//---------------------------------------------------
	

	public String getNickname() {
		return nickname;
	}



	public String getNick_name() {
		return nick_name;
	}



	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}



	public String getBoard_title() {
		return board_title;
	}



	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getR_user_no() {
		return r_user_no;
	}



	public void setR_user_no(int r_user_no) {
		this.r_user_no = r_user_no;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public int getBr_no() {
		return br_no;
	}



	public void setBr_no(int br_no) {
		this.br_no = br_no;
	}



	public int getBoard_no() {
		return board_no;
	}



	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}



	public int getRcount() {
		return rcount;
	}



	public void setRcount(int rcount) {
		this.rcount = rcount;
	}



	public int getAllcount() {
		return allcount;
	}



	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}



	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", report_content=" + report_content + ", report_date=" + report_date
				+ ", user_no=" + user_no + ", nick_name=" + nick_name + ", nickname=" + nickname + ", r_user_no="
				+ r_user_no + ", subject=" + subject + ", br_no=" + br_no + ", board_no=" + board_no + ", board_title="
				+ board_title + ", rcount=" + rcount + ", allcount=" + allcount + "]";
	}



	

}
