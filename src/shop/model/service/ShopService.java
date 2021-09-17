package shop.model.service;


import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import shop.model.dao.ShopDao;
import shop.model.vo.Product;

public class ShopService {
	private Properties query = new Properties();
	private ShopDao sd = new ShopDao();
	
	//1.조회리스트.
	public List<Product> selectList(){
		Connection conn = getConnection();
		List<Product> productList = sd.selectList(conn);
		close(conn);
		
		return productList;
	}
	
	
	
	
}
