package shop.model.vo;

import java.util.Date;

public class Charge {
	/*
	 * CHARGE_NO	NUMBER
		CHARGE_DATE	DATE
		CHARGE_COIN	NUMBER
		PAYMENT_METHOD	VARCHAR2(50 CHAR)
		USER_NO	NUMBER
	 * */
	
	private int chargeNo;
	private Date chargeDate;
	private int chargeCoin;
	private int userNo;
	
	public Charge(Date chargeDate, int chargeCoin) {
		super();
		this.chargeDate = chargeDate;
		this.chargeCoin = chargeCoin;
	}
	
	
	//충전완료시 필요한 값
	public Charge(int chargeCoin, int userNo) {
		super();
		this.chargeCoin = chargeCoin;
		this.userNo = userNo;
	}
	
	
	public int getChargeNo() {
		return chargeNo;
	}


	public void setChargeNo(int chargeNo) {
		this.chargeNo = chargeNo;
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public int getChargeCoin() {
		return chargeCoin;
	}
	public void setChargeCoin(int chargeCoin) {
		this.chargeCoin = chargeCoin;
	}
	

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "Charge [chargeNo=" + chargeNo + ", chargeDate=" + chargeDate + ", chargeCoin=" + chargeCoin
				+ ", userNo=" + userNo + "]";
	}
	
	
}

