package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecter {
	public static Connection getConnection(){
		Connection connection = null;
		
		try {
			String userName = "hr";
			String password = "hr";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection = DriverManager.getConnection(url, userName, password);
			
			System.out.println("연결 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알 수 없는 오류");
			e.printStackTrace();
		}
		
		return connection;
	}
}
