package common;

import java.util.Date;

public class Attachment {
	private int file_no;			// 파일번호
	private int board_no;			// 게시글번호
	private int notice_no;			// 공지사항번호
	private int s_no;				// 스터디방 번호
	private String origin_name;		// 파일 업로드시 이름
	private String change_name;		// 서버에 저장될 이름
	private String file_path;		// 파일 경로
	private Date upload_date;		// 업로드 시간
	private String status;			// 상태
	/*
	 FILE_NO	NUMBER
	BOARD_NO	NUMBER
	NOTICE_NO	NUMBER
	ORIGIN_NAME	VARCHAR2(255 CHAR)
	CHANGE_NAME	VARCHAR2(255 CHAR)
	FILE_PATH	VARCHAR2(1000 CHAR)
	UPLOAD_DATE	DATE
	STATUS	VARCHAR2(1 CHAR)
	*/
	
	
	public Attachment() {}
	
	public Attachment(String change_name, String file_path) {
		super();
		this.change_name = change_name;
		this.file_path = file_path;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public Attachment(int file_no, int board_no, int notice_no, int s_no, String origin_name, String change_name,
			String file_path, Date upload_date, String status) {
		super();
		this.file_no = file_no;
		this.board_no = board_no;
		this.notice_no = notice_no;
		this.s_no = s_no;
		this.origin_name = origin_name;
		this.change_name = change_name;
		this.file_path = file_path;
		this.upload_date = upload_date;
		this.status = status;
	}

	public Attachment(int file_no, int board_no, int notice_no, String origin_name, String change_name,
			String file_path, Date upload_date, String status) {
		super();
		this.file_no = file_no;
		this.board_no = board_no;
		this.notice_no = notice_no;
		this.origin_name = origin_name;
		this.change_name = change_name;
		this.file_path = file_path;
		this.upload_date = upload_date;
		this.status = status;
	}

	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public String getChange_name() {
		return change_name;
	}
	public void setChange_name(String change_name) {
		this.change_name = change_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [file_no=" + file_no + ", board_no=" + board_no + ", notice_no=" + notice_no + ", s_no="
				+ s_no + ", origin_name=" + origin_name + ", change_name=" + change_name + ", file_path=" + file_path
				+ ", upload_date=" + upload_date + ", status=" + status + "]";
	}
	
	
	
	
	
}
