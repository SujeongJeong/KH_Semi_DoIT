package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	// Connection 객체 리턴
	public static Connection getConnection() {
		Connection conn = null;
		Properties driver = new Properties();
		
		// driver 정보를 저장하여 제공할 수 있는 driver.properties 파일 생성
		// sql 패키지 하위에 driver.properties 파일 생성 후 파일 읽어오기
		// 클래스 객체 위치 찾기 : 절대 경로 반환
		// "/"로 시작하면  루트 디렉토리를 절대 경로로 url 객체로 반환
		String fileName = JDBCTemplate.class.getResource("/sql/driver.properties").getPath();
		// System.out.println(fileName);
		
		try {
			driver.load(new FileReader(fileName));
			
			// 0. webapp/WEB-INF/lib 에 ojdbc6.jar 라이브러리 추가
			
			// 1. driver 등록
			Class.forName(driver.getProperty("driver"));
			
			// 2. DBMS와 연결
			conn = DriverManager.getConnection(driver.getProperty("url"),
											   driver.getProperty("user"),
											   driver.getProperty("password"));
			
			// 트랜젝션 관리는 application에서 하기 위해 자동 commit 방지
			conn.setAutoCommit(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	// 사용 객체 close 구문
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
