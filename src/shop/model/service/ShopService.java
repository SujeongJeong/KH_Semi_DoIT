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
	public Product selectProduct(int product_no) {
		Connection conn = getConnection();
		Product p = sd.selectProduct(conn, product_no);
		close(conn);
		
		return p;
	}
	
	
	//상품 수정 
	public int modifyProduct(Product p) {
		
			Connection conn = getConnection();
			
			int result = sd.modifyProduct(conn, p);
			
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
	
	//상품삭제
	public int deleteProduct(int[] deleteProductArrInt) {
		Connection conn = getConnection();
		int resultSum = 0;
		for(int i=0; i<deleteProductArrInt.length; i++) {
		int result = sd.deleteProduct(conn, deleteProductArrInt[i]);
		resultSum += result;
	}
		
		
		
		if(resultSum > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return resultSum;

	}

	
	
	
	
	
	
}
