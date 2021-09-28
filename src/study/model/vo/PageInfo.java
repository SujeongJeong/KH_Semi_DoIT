package study.model.vo;

public class PageInfo {
	private int page;					// 요청 페이지
	private int listCount;				// 스터디방 수
	private int studyLimit = 10;		// 한 페이지에 보여질 스터디방 최대 수

	public PageInfo() {}
	
	public PageInfo(int page) {
		super();
		this.page = page;
//		this.studyLimit = page * 10;
	}

	public PageInfo(int page, int listCount) {
		super();
		this.page = page;
		this.listCount = listCount;
//		this.studyLimit = studyLimit;
		
	}

	public int getPage() {
		return page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public void setPage(int page) {
		this.page = page;
	}

//	public int getStudyLimit() {
//		return studyLimit;
//	}
//
//	public void setStudyLimit(int studyLimit) {
//		this.studyLimit = studyLimit;
//	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", studyLimit=" + studyLimit + "]";
	}
}
