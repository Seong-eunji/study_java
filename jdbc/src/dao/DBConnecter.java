package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecter {
	public static Connection getConnection() { 									// 메소드에서 매번 DB에 연결하면 번거로우니 static 메소드로 선언하여 사용
		Connection connection = null; 											// DB에 접속할 Connection을 담을 연결 객체 선언
		try {
			// 연결에 필요한 정보
			String userName = "hr"; 								// 연결 이름
			String password = "hr"; 								// 연결 비밀번호
			String url = "jdbc:oracle:thin:@localhost:1521:XE";	 	// 연결 url

			// 드라이버를 메모리에 할당
			Class.forName("oracle.jdbc.driver.OracleDriver");		// jar파일에 있는 드라이버를 찾아서 DB와 Java 파일을 연결

			// 정보를 전달하여 연결 객체 가져오기
			connection = DriverManager.getConnection(url, userName, password);

			System.out.println("연결 성공");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결 실패");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("알 수 없는 오류");
		}
		return connection; 														// 연결 성공하면 Connection타입, 연결 실패하면 null 리턴
	}
}