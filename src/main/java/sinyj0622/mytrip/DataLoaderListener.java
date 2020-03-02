package sinyj0622.mytrip;

import java.sql.Connection;
import java.util.Map;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.mariadb.BoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.MemberDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoBoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoFileDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PlanDaoImpl;
import sinyj0622.sql.DataSource;
import sinyj0622.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {
	// 다른 클래스에서 커넥션을 사용할 수 있도록 공개
	public static Connection con;

	@Override
	public void contextInitialized(Map<String, Object> context) {

		try {
			DataSource dataSource = 
					new DataSource("jdbc:mariadb://localhost:3306/studydb","study","1111");

			context.put("txManager", new PlatformTransactionManager(dataSource));
			context.put("boardDao", new BoardDaoImpl(dataSource));
			context.put("memberDao", new MemberDaoImpl(dataSource));
			context.put("planDao", new PlanDaoImpl(dataSource));
			context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
			context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));
			context.put("dataSource", dataSource);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(Map<String, Object> context) {
		DataSource dataSource = (DataSource) context.get("dataSource");
		dataSource.clean();
		
	}

}
