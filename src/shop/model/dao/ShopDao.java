package shop.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import shop.model.vo.Product;


public class ShopDao {
	//연결
	private Properties query = new Properties();
	public ShopDao() {
		String fileName = ShopDao.class.getResource("/sql/shop/shop-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//1. 리스트 조회
	public List<Product> selectList(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Product> productList = new ArrayList<>();
			String sql = query.getProperty("productList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					productList.add(new Product(rset.getInt("PRODUCT_NO"),
												rset.getString("PRODUCT_CATEGORY"),
												rset.getString("PRODUCT_NAME"),
												rset.getInt("EXPIRATION_DATE"),
												rset.getInt("PRODUCT_PRICE"),
												rset.getString("PRODUCT_DETAIL"),
												rset.getString("PRODUCT_IMG")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(conn);
			}
			return productList;
		}
	
	
	
	
	
	
	

}
