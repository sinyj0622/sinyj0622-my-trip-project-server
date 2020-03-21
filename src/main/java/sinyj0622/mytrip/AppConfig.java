package sinyj0622.mytrip;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.sql.MybatisDaoFactory;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.SqlSessionFactoryProxy;

@ComponentScan(value = "sinyj0622.mytrip")
public class AppConfig {

	  @Bean
	  public SqlSessionFactory sqlSessionFactory() throws Exception {
	    // Mabatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
	      InputStream inputStream = Resources.getResourceAsStream(//
	          "sinyj0622/mytrip/conf/mybatis-config.xml");

	    // 트랜잭션 제어를 위해 오리지널 객체 대신 프록시 객체에 담아 사용한다.
	    return  new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
	  }
	  
	  @Bean
	  MybatisDaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) {
	    // 이 메서드를 호출할 때 Spring IoC 컨테이너에 들어 있는 객체를 원한다면,
	    // 이렇게 파라미터로 선언하라.
	    // 그러면 Spring IoC 컨테이너가 이 팩토리 메서드를 호출하기 전에
	    // SqlSessionFactory 를 먼저 준비한 다음에 이 메서드를 실행할 것이다
	    return new MybatisDaoFactory(sqlSessionFactory);
	  }
	  
	  @Bean
	  public PlatformTransactionManager TransactionManager(
	      // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
	      // ***단 IoC 컨테이너에 들어 있는 값이어야 한다.***
	      SqlSessionFactory sqlSessionFactory) {
	    return new PlatformTransactionManager(sqlSessionFactory);
	  }
	  
	  @Bean
	  public BoardDao BoardDao(MybatisDaoFactory daoFactory) {
		  return daoFactory.createDao(BoardDao.class);
	  }
	  
	  @Bean
	  public PlanDao PlanDao(MybatisDaoFactory daoFactory) {
	    return daoFactory.createDao(PlanDao.class);
	  }

	  @Bean
	  public MemberDao MemberDao(MybatisDaoFactory daoFactory) {
	    return daoFactory.createDao(MemberDao.class);
	  }

	  @Bean
	  public PhotoBoardDao PhotoBoardDao(MybatisDaoFactory daoFactory) {
	    return daoFactory.createDao(PhotoBoardDao.class);
	  }

	  @Bean
	  public PhotoFileDao PhotoFileDao(MybatisDaoFactory daoFactory) {
	    return daoFactory.createDao(PhotoFileDao.class);
	  }
}
