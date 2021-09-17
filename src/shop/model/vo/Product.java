package shop.model.vo;

public class Product {
	
	/*PRDOUCT_NO	NUMBER
	PRODUCT_CATEGORY	VARCHAR2(50 CHAR)
	PRODUCT_NAME	VARCHAR2(100 CHAR)
	EXPIRATION_DATE	NUMBER
	PRODUCT_PRICE	NUMBER
	PRODUCT_DETAIL	VARCHAR2(800 CHAR)
	PRODUCT_IMG	VARCHAR2(300 CHAR)*/
	
	private int product_no;
	private String product_category;
	private String product_name;
	private int expiration_date;
	private int product_price;
	private String product_detail;
	private String product_img;
	
	
	
	
	public Product(int product_no, String product_category, String product_name, int expiration_date, int product_price,
			String product_detail, String product_img) {
		super();
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.expiration_date = expiration_date;
		this.product_price = product_price;
		this.product_detail = product_detail;
		this.product_img = product_img;
	}
	
	//상품리스트 조회용  구매shop 홈 리스트.
	
	public Product(int product_no, String product_category, String product_name, int product_price, String product_img) {
		super();
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_img = product_img;
	}
	
	
	public int getProduct_no() {
		return product_no;
	}
	

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(int expiration_date) {
		this.expiration_date = expiration_date;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_detail() {
		return product_detail;
	}
	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Product [product_no=" + product_no + ", product_category=" + product_category + ", product_name="
				+ product_name + ", expiration_date=" + expiration_date + ", product_price=" + product_price
				+ ", product_detail=" + product_detail + ", product_img=" + product_img + "]";
	}
	
	
	
	
	
	
}