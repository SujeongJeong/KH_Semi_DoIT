package study.model.vo;

public class PageInfo {
	private int page;					// 요청 페이지
	private int studyLimit;				// 한 페이지에 보여질 스터디방 최대 수
	private String searchTitle;			// 검색값
	private String categorySelected;	// 카테고리
	private boolean canJoinStudy;		// 참가가능한방
	
	public PageInfo() {}

	public PageInfo(int page) {
		super();
		this.page = page;
		this.studyLimit = page * 10;
	}

	public PageInfo(int page, String searchTitle, String categorySelected, boolean canJoinStudy) {
		super();
		this.page = page;
		this.searchTitle = searchTitle;
		this.categorySelected = categorySelected;
		this.canJoinStudy = canJoinStudy;

		this.studyLimit = page * 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStudyLimit() {
		return studyLimit;
	}

	public void setStudyLimit(int studyLimit) {
		this.studyLimit = studyLimit;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(String categorySelected) {
		this.categorySelected = categorySelected;
	}

	public boolean isCanJoinStudy() {
		return canJoinStudy;
	}

	public void setCanJoinStudy(boolean canJoinStudy) {
		this.canJoinStudy = canJoinStudy;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", studyLimit=" + studyLimit + ", searchTitle=" + searchTitle
				+ ", categorySelected=" + categorySelected + ", canJoinStudy=" + canJoinStudy + "]";
	}

	
	
	
	
}
