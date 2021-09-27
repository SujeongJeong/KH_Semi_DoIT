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

import static common.JDBCTemplate.*;

import shop.model.vo.Charge;
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
												rset.getInt("PRODUCT_PRICE"),
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

	
	//상품등록	
	public int insertProduct(Connection conn, Product p) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProduct_category());
			pstmt.setString(2, p.getProduct_name());
			pstmt.setInt(3, p.getExpiration_date());
			pstmt.setString(4, p.getProduct_detail());
			pstmt.setInt(5, p.getProduct_price());
			pstmt.setString(6, p.getProduct_img());
			
			result = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//상세게시글조회
	public Product selectProduct(Connection conn, int product_no) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String sql = query.getProperty("selectProduct");
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product(rset.getInt("PRODUCT_NO"),
							   rset.getString("PRODUCT_CATEGORY"),
							   rset.getString("PRODUCT_NAME"),
							   rset.getInt("EXPIRATION_DATE"),
							   rset.getInt("PRODUCT_PRICE"),
							   rset.getString("PRODUCT_DETAIL"),
							   rset.getString("PRODUCT_IMG"));
					}
	
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(pstmt);
				}
				return p;
			}

	//수정
	public int modifyProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("modifyProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProduct_category());
			pstmt.setString(2, p.getProduct_name());
			pstmt.setInt(3, p.getExpiration_date());
			pstmt.setString(4, p.getProduct_detail());
			pstmt.setInt(5, p.getProduct_price());
			pstmt.setString(6, p.getProduct_img());
			pstmt.setInt(7, p.getProduct_no());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//상품삭제

	public int deleteProduct(Connection conn, int product_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product_no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//충전 db등록
	public int insertCharge(Connection conn, Charge c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertCharge");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getChargeCoin());
			pstmt.setString(2, c.getPaymentMethod());
			pstmt.setInt(3, c.getUserNo());
			
			result = pstmt.executeUpdate();
			
			System.out.println("dao : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertOrder(Connection conn, int product_no, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product_no);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, product_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//잔여코인 dao
	public int updateCoin(Connection conn, int product_no, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateCoin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product_no);
			pstmt.setInt(2, userNo);
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
				
	
	
	
	
	

}
