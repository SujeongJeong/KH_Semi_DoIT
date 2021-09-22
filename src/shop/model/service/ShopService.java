package shop.model.service;


import static common.JDBCTemplate.*;

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

	//2. 상품추가
	public int insertProduct(Product p) {
		
		Connection conn = getConnection();
		int result = sd.insertProduct(conn, p);
	
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	//1개 상품 상세보기.
	public Product selectGallery(int pno) {
		Connection conn = getConnection();
		Product p = sd.selectProduct(conn, pno);
		close(conn);
		
		return p;
	}

	public int modifyProduct(Product p) {
		
			Connection conn = getConnection();
			
			int result = sd.modifyProduct(conn, p);
			
			System.out.println(p);
			
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
	
	
	
	
	
}
