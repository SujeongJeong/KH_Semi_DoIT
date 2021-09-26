package qna.model.vo;

import java.util.Date;

public class Report {
	private int report_no;
	private String report_content;
	private Date report_date;
	private int user_no;
	
	public Report() {}

	
	
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

	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", report_content=" + report_content + ", report_date=" + report_date
				+ ", user_no=" + user_no + "]";
	}
	
}
