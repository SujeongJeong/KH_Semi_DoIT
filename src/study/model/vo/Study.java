package study.model.vo;

import java.sql.Date;

public class Study {
	private int s_no;
	private String s_image;
	private String s_name;
	private int s_to;
	private String s_day;
	private Date s_startPeriod;
	private Date s_EndPeriod;
	private Date s_startTime;
	private Date s_EndTime;
	private String s_explain;
	private String s_notice;
	private String s_status;
	private int user_no;
	private int cid;
	
	public Study() {}

	
	
	public Study(String s_image, String s_name, int s_to, String s_day, String s_explain, String s_notice) {
		super();
		this.s_image = s_image;
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
	}



	public Study(String s_image, String s_name, int s_to, String s_day, Date s_startPeriod, 
			Date s_EndPeriod, Date s_startTime,	Date s_EndTime, String s_explain, String s_notice) {
		super();
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_startPeriod = s_startPeriod;
		this.s_EndPeriod = s_EndPeriod;
		this.s_startTime = s_startTime;
		this.s_EndTime = s_EndTime;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
		this.s_image = s_image;
	}

	public Study(int s_no, String s_name, int s_to, String s_day, Date s_startPeriod, Date s_EndPeriod,
			Date s_startTime, Date s_EndTime, String s_explain, String s_notice, String s_image, String s_status,
			int user_no, int cid) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_startPeriod = s_startPeriod;
		this.s_EndPeriod = s_EndPeriod;
		this.s_startTime = s_startTime;
		this.s_EndTime = s_EndTime;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
		this.s_image = s_image;
		this.s_status = s_status;
		this.user_no = user_no;
		this.cid = cid;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public int getS_to() {
		return s_to;
	}

	public void setS_to(int s_to) {
		this.s_to = s_to;
	}

	public String getS_day() {
		return s_day;
	}

	public void setS_day(String s_day) {
		this.s_day = s_day;
	}

	public Date getS_startPeriod() {
		return s_startPeriod;
	}

	public void setS_startPeriod(Date s_startPeriod) {
		this.s_startPeriod = s_startPeriod;
	}

	public Date getS_EndPeriod() {
		return s_EndPeriod;
	}

	public void setS_EndPeriod(Date s_EndPeriod) {
		this.s_EndPeriod = s_EndPeriod;
	}

	public Date getS_startTime() {
		return s_startTime;
	}

	public void setS_startTime(Date s_startTime) {
		this.s_startTime = s_startTime;
	}

	public Date getS_EndTime() {
		return s_EndTime;
	}

	public void setS_EndTime(Date s_EndTime) {
		this.s_EndTime = s_EndTime;
	}

	public String getS_explain() {
		return s_explain;
	}

	public void setS_explain(String s_explain) {
		this.s_explain = s_explain;
	}

	public String getS_notice() {
		return s_notice;
	}

	public void setS_notice(String s_notice) {
		this.s_notice = s_notice;
	}

	public String getS_image() {
		return s_image;
	}

	public void setS_image(String s_image) {
		this.s_image = s_image;
	}

	public String getS_status() {
		return s_status;
	}

	public void setS_status(String s_status) {
		this.s_status = s_status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Study [s_no=" + s_no + ", s_name=" + s_name + ", s_to=" + s_to + ", s_day=" + s_day + ", s_startPeriod="
				+ s_startPeriod + ", s_EndPeriod=" + s_EndPeriod + ", s_startTime=" + s_startTime + ", s_EndTime="
				+ s_EndTime + ", s_explain=" + s_explain + ", s_notice=" + s_notice + ", s_image=" + s_image
				+ ", s_status=" + s_status + ", user_no=" + user_no + ", cid=" + cid + "]";
	}
	
	
}
