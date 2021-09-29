package qna.model.vo;

public class Search {
	/* private String searchCondition; */
	private String searchValue;
	private String category;
	private String range;
	
	public Search() {}

	public Search(String searchValue) {
		super();
		this.searchValue = searchValue;
	}
	
	public Search(String category, String range) {
		super();
		this.category = category;
		this.range = range;
	}

	
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return "Search [searchValue=" + searchValue + ", category=" + category + ", range=" + range + "]";
	}

	
	
	
	
	/*
	 * public Search(String searchCondition, String searchValue) { super();
	 * this.searchCondition = searchCondition; this.searchValue = searchValue; }
	 * 
	 * public String getSearchCondition() { return searchCondition; }
	 * 
	 * public void setSearchCondition(String searchCondition) { this.searchCondition
	 * = searchCondition; }
	 * 
	 * public String getSearchValue() { return searchValue; }
	 * 
	 * public void setSearchValue(String searchValue) { this.searchValue =
	 * searchValue; }
	 * 
	 * @Override public String toString() { return "Search [searchCondition=" +
	 * searchCondition + ", searchValue=" + searchValue + "]"; }
	 */
	
	
}
