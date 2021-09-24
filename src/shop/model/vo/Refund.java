package shop.model.vo;

import java.util.Date;

public class Refund {
	/*
	 * REFUND_NO	NUMBER
		REFUND_COIN	NUMBER
		REFUND_DATE	DATE
		BANK_ACCOUNT	NUMBER
		BANK_NAME	VARCHAR2(15 CHAR)
		ACCOUNT_NAME	VARCHAR2(20 CHAR)
		COMPLETE_DATE	DATE
		USER_NO	NUMBER
	 * 
	 * */
	private int refundNo; // 환불번호
	private int refundCoin;	// 환불코인가격
	private Date refundDate;	// 환불신청일
	private String bank_account;	// 계좌번호
	private String bankName;	// 은행
	private String accountName;	// 예금주
	private Date completeDate; //환불처리시간
	private int userNo;	// 회원번호
	
	public Refund(int refundCoin, String bank_account, String bankName, String accountName, int userNo) {
		super();
		this.refundCoin = refundCoin;
		this.bank_account = bank_account;
		this.bankName = bankName;
		this.accountName = accountName;
		this.userNo = userNo;
	}

	public int getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(int refundNo) {
		this.refundNo = refundNo;
	}

	public int getRefundCoin() {
		return refundCoin;
	}

	public void setRefundCoin(int refundCoin) {
		this.refundCoin = refundCoin;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "Refund [refundNo=" + refundNo + ", refundCoin=" + refundCoin + ", refundDate=" + refundDate
				+ ", bank_account=" + bank_account + ", bankName=" + bankName + ", accountName=" + accountName
				+ ", completeDate=" + completeDate + ", userNo=" + userNo + "]";
	}

	
}
