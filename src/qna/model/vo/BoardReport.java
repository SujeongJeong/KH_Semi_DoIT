package qna.model.vo;

public class BoardReport {
	private int board_no;
	private int report_no;
	
	public BoardReport() {}

	
	
	
	
	public BoardReport(int board_no) {
		super();
		this.board_no = board_no;
	}

	public BoardReport(int board_no, int report_no) {
		super();
		this.board_no = board_no;
		this.report_no = report_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	@Override
	public String toString() {
		return "BoardReport [board_no=" + board_no + ", report_no=" + report_no + "]";
	}
	
	
}
