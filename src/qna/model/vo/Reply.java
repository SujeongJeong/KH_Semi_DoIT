package qna.model.vo;

import java.util.Date;

public class Reply {
	private int rid;
	private String rcontent;
	private int refBid;
	private int rwriter;
	private String userName;
	private Date createDate;
	private Date modifyDate;
	private String status;
//	RID	NUMBER
//	RCONTENT	VARCHAR2(400 BYTE)
//	REF_BID	NUMBER
//	RWRITER	NUMBER
//	CREATE_DATE	DATE
//	MODIFY_DATE	DATE
//	STATUS	VARCHAR2(1 BYTE)
	public Reply() {}
	
	public Reply(int rid, String rcontent, int refBid, int rwriter, String userName, Date createDate) {
	super();
	this.rid = rid;
	this.rcontent = rcontent;
	this.refBid = refBid;
	this.rwriter = rwriter;
	this.userName = userName;
	this.createDate = createDate;
}



	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public int getRefBid() {
		return refBid;
	}
	public void setRefBid(int refBid) {
		this.refBid = refBid;
	}
	public int getRwriter() {
		return rwriter;
	}
	public void setRwriter(int rwriter) {
		this.rwriter = rwriter;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", rcontent=" + rcontent + ", refBid=" + refBid + ", rwriter=" + rwriter
				+ ", userName=" + userName + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status="
				+ status + "]";
	}



}
