package study.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.Attachment;

public class Study {
	private int s_no; 										 // 스터디방 번호 pk
	private String s_name;									 // 스터디방 이름
	private int s_to; 										 // 스터디방 정원
	private String s_day;									 // 스터디방 여는 요일
	private Date s_startPeriod;								 // 시작일
	private Date s_endPeriod;								 // 종료일
	private Date s_startTime;								 // 시작시간
	private Date s_endTime;									 // 종료시간
	private String s_explain;								 // 스터디방 설명
	private String s_notice;								 // 스터디방 공지
	private String s_status;								 // 스터디방 상태
	private int user_no;									 // 방장 계정 번호 - session의 loginUser에서 가져올 값
	private int cid;										 // 카테고리 번호
	private String cname;									 // 카테고리 이름 - category 조인 값
	private String user_nkname;								 // 방장 닉네임 - member 조인 값
	private List<Attachment> sImgList = new ArrayList<>();	 // 설정된 스터디방 파일(배경이미지)정보 리스트
	private int studyMemberNum;								 // 스터디방에 가입된 회원 숫자(member_joinstudy 조인)
	
	public Study() {}
	
	public Study(int s_no, int studyMemberNum) {
		super();
		this.s_no = s_no;
		this.studyMemberNum = studyMemberNum;
	}

	public Study(String s_name, int s_to, String s_day, String s_explain, String s_notice, int user_no, int cid,
			List<Attachment> sImgList) {
		super();
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
		this.user_no = user_no;
		this.cid = cid;
		this.sImgList = sImgList;
	}

	public Study(String s_name, int s_to, String s_day, Date s_startPeriod, Date s_endPeriod, Date s_startTime,
			Date s_endTime, String s_explain, String s_notice, int user_no, int cid, List<Attachment> sImgList) {
		super();
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_startPeriod = s_startPeriod;
		this.s_endPeriod = s_endPeriod;
		this.s_startTime = s_startTime;
		this.s_endTime = s_endTime;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
		this.user_no = user_no;
		this.cid = cid;
		this.sImgList = sImgList;
	}

	public Study(int s_no, String s_name, int s_to, String s_day, Date s_startPeriod, Date s_endPeriod,
			Date s_startTime, Date s_endTime, String s_explain, String s_notice, String cname, String user_nkname,
			String change_name, String file_path) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_startPeriod = s_startPeriod;
		this.s_endPeriod = s_endPeriod;
		this.s_startTime = s_startTime;
		this.s_endTime = s_endTime;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
		this.cname = cname;
		this.user_nkname = user_nkname;
		this.sImgList.add(new Attachment(change_name, file_path));
	}

	public Study(int s_no, String cname, String s_name, int s_to, String user_nkname, Date s_startPeriod, Date s_endPeriod) {
	      super();
	      this.s_no = s_no;
	      this.s_name = s_name;
	      this.s_to = s_to;
	      this.s_startPeriod = s_startPeriod;
	      this.s_endPeriod = s_endPeriod;
	      this.cname = cname;
	      this.user_nkname = user_nkname;
	   }
	
	public Study(int s_no, String s_name, int s_to, String s_day, Date s_startPeriod, Date s_endPeriod,
			Date s_startTime, Date s_endTime, String s_explain, String s_notice, String s_status, int user_no, int cid,
			String cname, String user_nkname, List<Attachment> sImgList, int studyMemberNum) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_to = s_to;
		this.s_day = s_day;
		this.s_startPeriod = s_startPeriod;
		this.s_endPeriod = s_endPeriod;
		this.s_startTime = s_startTime;
		this.s_endTime = s_endTime;
		this.s_explain = s_explain;
		this.s_notice = s_notice;
		this.s_status = s_status;
		this.user_no = user_no;
		this.cid = cid;
		this.cname = cname;
		this.user_nkname = user_nkname;
		this.sImgList = sImgList;
		this.studyMemberNum = studyMemberNum;
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

	public Date gets_endPeriod() {
		return s_endPeriod;
	}

	public void sets_endPeriod(Date s_endPeriod) {
		this.s_endPeriod = s_endPeriod;
	}

	public Date getS_startTime() {
		return s_startTime;
	}

	public void setS_startTime(Date s_startTime) {
		this.s_startTime = s_startTime;
	}

	public Date gets_endTime() {
		return s_endTime;
	}

	public void sets_endTime(Date s_endTime) {
		this.s_endTime = s_endTime;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUser_nkname() {
		return user_nkname;
	}

	public void setUser_nkname(String user_nkname) {
		this.user_nkname = user_nkname;
	}

	public List<Attachment> getsImgList() {
		return sImgList;
	}

	public void setsImgList(List<Attachment> sImgList) {
		this.sImgList = sImgList;
	}



	@Override
	public String toString() {
		return "Study [s_no=" + s_no + ", s_name=" + s_name + ", s_to=" + s_to + ", s_day=" + s_day + ", s_startPeriod="
				+ s_startPeriod + ", s_endPeriod=" + s_endPeriod + ", s_startTime=" + s_startTime + ", s_endTime="
				+ s_endTime + ", s_explain=" + s_explain + ", s_notice=" + s_notice + ", s_status=" + s_status
				+ ", user_no=" + user_no + ", cid=" + cid + ", cname=" + cname + ", user_nkname=" + user_nkname
				+ ", sImgList=" + sImgList + ", studyMemberNum=" + studyMemberNum + "]";
	}



}
