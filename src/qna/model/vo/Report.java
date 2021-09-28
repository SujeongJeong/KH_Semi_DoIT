package qna.model.vo;

import java.util.Date;

public class Report {
	private int report_no;
	private String report_content;
	private Date report_date;
	private int user_no;
	
	private int Ruser_no;	// join 값 (신고당한 유저 번호)
	private int r_count;	// join 값(신고 당한 count)
	private int m_count;	// join 값(신고 당한 회원 count)
	
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
	
	public Report(int report_no, String report_content, Date report_date, int user_no, int ruser_no, int r_count,
			int m_count) {
		super();
		this.report_no = report_no;
		this.report_content = report_content;
		this.report_date = report_date;
		this.user_no = user_no;
		this.Ruser_no = ruser_no;
		this.r_count = r_count;
		this.m_count = m_count;
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
	
	public int getRuser_no() {
		return Ruser_no;
	}



	public void setRuser_no(int ruser_no) {
		Ruser_no = ruser_no;
	}



	public int getR_count() {
		return r_count;
	}



	public void setR_count(int r_count) {
		this.r_count = r_count;
	}



	public int getM_count() {
		return m_count;
	}



	public void setM_count(int m_count) {
		this.m_count = m_count;
	}
	
	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", report_content=" + report_content + ", report_date=" + report_date
				+ ", user_no=" + user_no + "]";
	}
	
}
