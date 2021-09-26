package qna.model.vo;

public class ReplyReport {
	private int reply_no;
	private int report_no;
	
	public ReplyReport() {}

	
	
	public ReplyReport(int reply_no) {
		super();
		this.reply_no = reply_no;
	}



	public ReplyReport(int reply_no, int report_no) {
		super();
		this.reply_no = reply_no;
		this.report_no = report_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	@Override
	public String toString() {
		return "ReplyReport [reply_no=" + reply_no + ", report_no=" + report_no + "]";
	}
	
	
}
