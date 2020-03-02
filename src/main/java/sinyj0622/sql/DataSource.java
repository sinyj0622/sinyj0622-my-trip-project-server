package sinyj0622.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSource {

	String jdbcUrl;
	String username;
	String password;

	// 스레드에 커넥션 보관
	ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();
	// 반납받은 커넥션 보관
	ArrayList<Connection> conList = new ArrayList<>();

	public DataSource(String jdbcUrl, String username, String password) {
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
			if (conList.size() > 0) {
				con = conList.remove(0);
				
			} else {
				con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));

			}
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
			
			conList.add(con); //커넥션 반납
		}
		return con;
	}
	
	public void clean() {
		while(conList.size() > 0) {
			try {
				((ConnectionProxy)conList.remove(0)).realClose();
			} catch (SQLException e) {
				//
			}
		}
	}
}
