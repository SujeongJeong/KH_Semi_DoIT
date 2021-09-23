package qna.model.vo;

import java.util.Date;
import java.util.List;


public class Board {
	
	private int board_no;
	private int cid;
	private String cname;
	private String board_title;
	private String nickname;
	private String board_content;
	private Date create_date;
	private Date modify_date;
	private String status;
	private String board_secret;
	private int user_no;
	private int count;
	private List<Reply> replyList;
	
	
	public Board() {}
	
	
	
	public Board(int board_no, int cid, String board_title, String board_content) {
		super();
		this.board_no = board_no;
		this.cid = cid;
		this.board_title = board_title;
		this.board_content = board_content;
	}



	public Board(int cid, String board_title, String board_content, int user_no) {
		super();
		this.cid = cid;
		this.board_title = board_title;
		this.board_content = board_content;
		this.user_no = user_no;
	}


	public Board(int board_no, String cname, String board_title, String nickname, Date create_date, int count) {
		super();
		this.board_no = board_no;
		this.cname = cname;
		this.board_title = board_title;
		this.nickname = nickname;
		this.create_date = create_date;
		this.count = count;
	}



	public Board(int board_no, String cname, String board_title, String nickname, String board_content,
			Date create_date, Date modify_date, String status, String board_secret, int count) {
		super();
		this.board_no = board_no;
		this.cname = cname;
		this.board_title = board_title;
		this.nickname = nickname;
		this.board_content = board_content;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.status = status;
		this.board_secret = board_secret;
		this.count = count;
	}

	public Board(int board_no, int cid, String cname, String board_title, String nickname, String board_content,
			Date create_date, Date modify_date, int user_no, int count) {
		super();
		this.board_no = board_no;
		this.cid = cid;
		this.cname = cname;
		this.board_title = board_title;
		this.nickname = nickname;
		this.board_content = board_content;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.user_no = user_no;
		this.count = count;
	}

	
	public Board(int board_no, int cid, String cname, String board_title, String nickname, String board_content,
			Date create_date, Date modify_date, String status, String board_secret, int user_no, int count,
			List<Reply> replyList) {
		super();
		this.board_no = board_no;
		this.cid = cid;
		this.cname = cname;
		this.board_title = board_title;
		this.nickname = nickname;
		this.board_content = board_content;
		this.create_date = create_date;
		this.modify_date = modify_date;
		this.status = status;
		this.board_secret = board_secret;
		this.user_no = user_no;
		this.count = count;
		this.replyList = replyList;
	}



	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
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

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
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

	public String getBoard_secret() {
		return board_secret;
	}

	public void setBoard_secret(String board_secret) {
		this.board_secret = board_secret;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}



	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", cid=" + cid + ", cname=" + cname + ", board_title=" + board_title
				+ ", nickname=" + nickname + ", board_content=" + board_content + ", create_date=" + create_date
				+ ", modify_date=" + modify_date + ", status=" + status + ", board_secret=" + board_secret
				+ ", user_no=" + user_no + ", count=" + count + ", replyList=" + replyList + "]";
	}


}
