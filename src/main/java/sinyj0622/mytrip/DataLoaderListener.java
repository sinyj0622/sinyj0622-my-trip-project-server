package sinyj0622.mytrip;

import java.sql.Connection;
import java.util.Map;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.mariadb.BoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.MemberDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoBoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoFileDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PlanDaoImpl;
import sinyj0622.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {
	// 다른 클래스에서 커넥션을 사용할 수 있도록 공개
	public static Connection con;

	@Override
	public void contextInitialized(Map<String, Object> context) {

		try {
			ConnectionFactory conFactory = 
					new ConnectionFactory("jdbc:mariadb://localhost:3306/studydb","study","1111");

			context.put("boardDao", new BoardDaoImpl(conFactory));
			context.put("memberDao", new MemberDaoImpl(conFactory));
			context.put("planDao", new PlanDaoImpl(conFactory));
			context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
			context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));


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
