package shop.model.vo;

public class Product {
	
	/*PRDOUCT_NO	NUMBER
	PRODUCT_CATEGORY	VARCHAR2(50 CHAR)
	PRODUCT_NAME	VARCHAR2(100 CHAR)
	EXPIRATION_DATE	NUMBER
	PRODUCT_PRICE	NUMBER
	PRODUCT_DETAIL	VARCHAR2(800 CHAR)
	PRODUCT_IMG	VARCHAR2(300 CHAR)
	S_LIMIT	NUMBER
	S_TO_LIMIT	NUMBER
	TODO_LIMIT	NUMBER
	*/
	
	private int product_no;
	private String product_category;
	private String product_name;
	private int expiration_date;
	private int product_price;
	private String product_detail;
	private String product_img;
	private int s_limit;
	private int s_to_limit;
	private int todo_limit;
	
	public Product(){}
	
	
	//전체용 + 수정용
	public Product(int product_no, String product_category, String product_name, int expiration_date, int product_price,
			String product_detail, String product_img, int s_limit, int s_to_limit, int todo_limit) {
		super();
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.expiration_date = expiration_date;
		this.product_price = product_price;
		this.product_detail = product_detail;
		this.product_img = product_img;
		this.s_limit = s_limit;
		this.s_to_limit = s_to_limit;
		this.todo_limit = todo_limit;
	}




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
	
	
	//상품리스트 조회용  구매shop 홈 상품 리스트.
	public Product(int product_no, String product_category, String product_name, int product_price, String product_img) {
		super();
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_img = product_img;
	}
	
	//인서트용
	public Product(String product_category, String product_name, int product_price, int expiration_date, int s_limit, int s_to_limit, int todo_limit, String product_detail,
			String product_img) {
		super();
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.expiration_date = expiration_date;
		this.s_limit = s_limit;
		this.s_to_limit = s_to_limit;
		this.todo_limit = todo_limit;
		this.product_detail = product_detail;
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



	public int getS_limit() {
		return s_limit;
	}



	public void setS_limit(int s_limit) {
		this.s_limit = s_limit;
	}



	public int getS_to_limit() {
		return s_to_limit;
	}



	public void setS_to_limit(int s_to_limit) {
		this.s_to_limit = s_to_limit;
	}



	public int getTodo_limit() {
		return todo_limit;
	}



	public void setTodo_limit(int todo_limit) {
		this.todo_limit = todo_limit;
	}



	@Override
	public String toString() {
		return "Product [product_no=" + product_no + ", product_category=" + product_category + ", product_name="
				+ product_name + ", expiration_date=" + expiration_date + ", product_price=" + product_price
				+ ", product_detail=" + product_detail + ", product_img=" + product_img + ", s_limit=" + s_limit
				+ ", s_to_limit=" + s_to_limit + ", todo_limit=" + todo_limit + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}