package sinyj0622.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	String jdbcUrl;
	String username;
	String password;

	// 스레드에 값을 보관할 도구 준비
	ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

	public ConnectionFactory(String jdbcUrl, String username, String password) {
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws Exception {
		// 스레드에 보관된 커넥션 객체가 있는지 확인
		Connection con = connectionLocal.get();
		// 있다면 보관된 객체를 리턴
		if (con != null) {
			System.out.println("스레드에 보관된 커넥션 객체 리턴");
			return con;
			
		} else {
			// 없다면 새 커넥션을 생성한다
			con = DriverManager.getConnection(jdbcUrl, username, password);
			// 리턴하기 전에 스레드에 커넥션의 주소를 기록한다.
			connectionLocal.set(con);
			return con;
		}
	}
	
	public void removeConnection() {
		// 현재 스레드에 커넥션 얻기
		Connection con = connectionLocal.get();
		if (con != null) { 
			connectionLocal.remove(); // 커넥션 제거
		}
	}
}
