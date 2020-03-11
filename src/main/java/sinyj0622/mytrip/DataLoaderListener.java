package sinyj0622.mytrip;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.mariadb.BoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.MemberDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoBoardDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PhotoFileDaoImpl;
import sinyj0622.mytrip.dao.mariadb.PlanDaoImpl;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {
  // 다른 클래스에서 커넥션을 사용할 수 있도록 공개
  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // Mabatis 객체 준비
      InputStream inputStream = Resources.getResourceAsStream(//
          "sinyj0622/mytrip/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));


      context.put("sqlSessionFactory", sqlSessionFactory);

      context.put("txManager", new PlatformTransactionManager(sqlSessionFactory));
      context.put("boardDao", new BoardDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("planDao", new PlanDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }

}
