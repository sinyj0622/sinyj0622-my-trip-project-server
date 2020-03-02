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
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection( //
					"jdbc:mariadb://localhost:3306/studydb", "study", "1111");

			context.put("boardDao", new BoardDaoImpl(con));
			context.put("memberDao", new MemberDaoImpl(con));
			context.put("planDao", new PlanDaoImpl(con));
			context.put("photoBoardDao", new PhotoBoardDaoImpl(con));
			context.put("photoFileDao", new PhotoFileDaoImpl(con));
			
			
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
