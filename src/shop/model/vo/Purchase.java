package shop.model.vo;

import java.util.Date;

public class Purchase {
	/*
	 *  PURCHASE_NO	NUMBER
		PRODUCT_COUNT	NUMBER
		PURCHASE_DATE	DATE
		START_DATE	DATE
		EXPIRATION_DATE	DATE
		USER_NO	NUMBER
		PRDOUCT_NO	NUMBER
		아이템명 - Product 테이블
		
	 * 
	 * 
	 * 
	 * */
	private int purchaseNo; // 구매번호
	private int productCount; // 구매수량
	private Date purchaseDate; // 구매일
	private Date startDate; // 구매시작일
	private Date expirationDate; // 유효기간
	private int userNo; // Member 테이블 조인  구매자 번호
	private int productNo; // Product 테이블 조인 상품번호
	private int productPrice; // Product 상품 코인가격 
	private String productName; //  Product 테이블 조인
	
	public Purchase(String productName, Date startDate, Date expirationDate, int productPrice) {
		super();
		this.productName = productName;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
		this.productPrice = productPrice;
	}

	
	public Purchase(Date purchaseDate, String productName, int productCount, int productPrice) {
		super();
		this.purchaseDate = purchaseDate;
		this.productPrice = productPrice;
		this.productName = productName;
		this.productCount = productCount;
	}


	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseNo=" + purchaseNo + ", productCount=" + productCount + ", purchaseDate="
				+ purchaseDate + ", startDate=" + startDate + ", expirationDate=" + expirationDate + ", userNo="
				+ userNo + ", productNo=" + productNo + ", productPrice=" + productPrice + ", productName="
				+ productName + "]";
	}
}
