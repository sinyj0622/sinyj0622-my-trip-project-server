package sinyj0622.util;

import java.sql.Connection;
import java.sql.DriverManager;

import sinyj0622.sql.ConnectionProxy;

public class ConnectionFactory {

	String jdbcUrl;
	String username;
	String password;

	ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

	public ConnectionFactory(String jdbcUrl, String username, String password) {
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws Exception {
		Connection con = connectionLocal.get();
		if (con != null) {
			System.out.println("스레드에 보관된 커넥션 객체 리턴");
			return con;
			
		} else {
			con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));
			// 리턴하기 전에 스레드에 커넥션의 주소를 기록한다.
			connectionLocal.set(con);
			return con;
		}
	}
	
	public Connection removeConnection() {
		// 현재 스레드에 커넥션 얻기
		Connection con = connectionLocal.get();
		if (con != null) { 
			connectionLocal.remove(); // 커넥션 제거
		}
		return con;
	}
}
