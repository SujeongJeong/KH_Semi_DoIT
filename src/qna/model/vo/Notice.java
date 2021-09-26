package qna.model.vo;

import java.util.Date;
import java.util.List;

public class Notice {

	private int notice_no;
	private String notice_title;
	private String notice_content;
	private int count;
	private Date create_date;
	private Date modify_date;
	private String status;
	private int user_no;
	private List<Reply> replyList;
	
//	notice_no	NUMBER
//	notice_title	VARCHAR2(100 CHAR)
//	notice_content	VARCHAR2(4000 CHAR)
//	count	NUMBER
//	create_date	DATE
//	modify_date	DATE
//	status	VARCHAR2(1 CHAR)
//	user_no	NUMBER

	public Notice() {}
	
	

	public Notice(int notice_no, String notice_title, String notice_content) {
	super();
	this.notice_no = notice_no;
	this.notice_title = notice_title;
	this.notice_content = notice_content;
	}



	public Notice(int notice_no, String notice_title, String notice_content, int count, Date create_date,
			Date modify_date, String status, int user_no) {
		super();
		this.notice_no = notice_no;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.count = count;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.status = status;
		this.user_no = user_no;
	}

//	public Notice(String notice_title, String notice_content, int user_no) {
//		super();
//		this.notice_title = notice_title;
//		this.notice_content = notice_content;
//		this.user_no = user_no;
//	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	
	public List<Reply> getReplyList() {
		return replyList;
	}
	

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}



	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", count=" + count + ", create_date=" + create_date + ", modify_date=" + modify_date
				+ ", status=" + status + ", user_no=" + user_no + ", replyList=" + replyList + "]";
	}


}
