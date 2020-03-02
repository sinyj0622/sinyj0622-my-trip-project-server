package sinyj0622.mytrip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.mariadb.BoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.MemberDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoBoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoFileDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PlanDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
	// 다른 클래스에서 커넥션을 사용할 수 있도록 공개
	public static Connection con;

	@Override
	public void contextInitialized(Map<String, Object> context) {

		try {
			// DB 연결정보
			Class.forName("org.mariadb.jdbc.Driver");
			String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
			String usename = "study";
			String password = "1111";



			context.put("boardDao", new BoardDaoImpl(jdbcUrl, usename, password));
			context.put("memberDao", new MemberDaoImpl(jdbcUrl, usename, password));
			context.put("planDao", new PlanDaoImpl(jdbcUrl, usename, password));
			context.put("photoBoardDao", new PhotoBoardDaoImpl(jdbcUrl, usename, password));
			context.put("photoFileDao", new PhotoFileDaoImpl(jdbcUrl, usename, password));


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(Map<String, Object> context) {

		try{
			con.close();
		} catch	(Exception e) {

		}
	}

}
