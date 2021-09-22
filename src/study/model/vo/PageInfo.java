package study.model.vo;

public class PageInfo {
	private int page;			// 요청 페이지
	private int listCount;		// 스터디방 총 갯수
	private int StudyLimit;		// 한 페이지에 보여질 스터디방 최대 수
	
	public PageInfo() {}

	public PageInfo(int page, int listCount, int studyLimit) {
		super();
		this.page = page;
		this.listCount = listCount;
		StudyLimit = studyLimit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getStudyLimit() {
		return StudyLimit;
	}

	public void setStudyLimit(int studyLimit) {
		StudyLimit = studyLimit;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", StudyLimit=" + StudyLimit + "]";
	}
	
	
	
	
	
	
	
}
