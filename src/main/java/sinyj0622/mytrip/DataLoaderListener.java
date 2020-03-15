package sinyj0622.mytrip;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.service.Impl.BoardServiceImpl;
import sinyj0622.mytrip.service.Impl.BoardServiceImpl2;
import sinyj0622.mytrip.service.Impl.MemberServiceImpl;
import sinyj0622.mytrip.service.Impl.PhotoBoardServiceImpl;
import sinyj0622.mytrip.service.Impl.PlanServiceImpl;
import sinyj0622.sql.MybatisDaoFactory;
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
      
      // DAO 프록시 객체를 생성해 줄 Factory를 준비
      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);
      
      // 서비스 객체가 사용할 DAO를 준비한다.
      BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      PlanDao planDao = daoFactory.createDao(PlanDao.class);
      PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);
      
      // 트랜잭션 관리자 준비
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      
      context.put("photoBoardService", new PhotoBoardServiceImpl(photoBoardDao,photoFileDao,txManager));
      context.put("planService", new PlanServiceImpl(planDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("boardService", new BoardServiceImpl2(sqlSessionFactory));
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }

}
