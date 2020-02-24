package sinyj0622.mytrip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.dao.mariadb.BoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.MemberDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PlanDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

	Connection con;

	@Override
	public void contextInitialized(Map<String, Object> context) {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection( //
					"jdbc:mariadb://localhost:3306/studydb", "study", "1111");

			context.put("boardDao", new BoardDaoImpl(con));
			context.put("memberDao", new MemberDaoImpl(con));
			context.put("planDao", new PlanDaoImpl(con));
			
			
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
