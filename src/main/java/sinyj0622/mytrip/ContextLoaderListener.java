package sinyj0622.mytrip;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
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
import sinyj0622.sql.MybatisDaoFactory;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.SqlSessionFactoryProxy;
import sinyj0622.util.ApplicationContext;
import sinyj0622.util.Component;
import sinyj0622.util.RequestHandler;
import sinyj0622.util.RequestMapping;
import sinyj0622.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {
  // 다른 클래스에서 커넥션을 사용할 수 있도록 공개
  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // ApplicationContext에서 자동으로 생성하지 못하는 객체는
      // 따로 생성하여 Map에 보관
      HashMap<String, Object> beans = new HashMap<>();

      // Mabatis 객체 준비
      InputStream inputStream = Resources.getResourceAsStream(//
          "sinyj0622/mytrip/conf/mybatis-config.xml");

      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
      beans.put("sqlSessionFactory", sqlSessionFactory);

      // DAO 프록시 객체를 생성해 줄 Factory를 준비
      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      // 서비스 객체가 사용할 DAO를 준비한다.
      beans.put("boardDao", daoFactory.createDao(BoardDao.class));
      beans.put("memberDao", daoFactory.createDao(MemberDao.class));
      beans.put("planDao", daoFactory.createDao(PlanDao.class));
      beans.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));

      // 트랜잭션 관리자 준비
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      beans.put("transactionManager", txManager);

      // IoC 컨테이너 준비
      ApplicationContext appCtx = new ApplicationContext("sinyj0622.mytrip", beans);
      appCtx.printBeans();

      // ServerApp이 사용할 수 있게 context 맵에 보관
      context.put("iocContainer", appCtx);

      System.out.println("---------------------------------------");

      // @Component 애노테이션이 붙은 객체를 찾는다.
      RequestMappingHandlerMapping handlerMapper = //
          new RequestMappingHandlerMapping();

      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);

        // @RequestHandler가 붙은 메서드를 찾는다.
        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          // 클라이언트 명령을 처리하는 메서드 정보를 준비한다.
          RequestHandler requestHandler = new RequestHandler(method, component);

          // 명령을 처리할 메서드를 찾을 수 있도록
          // 명령 이름으로 메서드 정보를 저장한다.
          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }

      // ServerApp 에서 request handler를 사용할 수 있도록 공유한다.
      context.put("handlerMapper", handlerMapper);


    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private Method getRequestHandler(Class<?> type) {
    // 클라이언트 명령을 처리할 메서드는 public 이기 때문에
    // 클래스에서 public 메서드만 조사한다.
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      // 메서드에 @RequestMapping 애노테이션이 붙었는지 검사한다.
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        return m;
      }
    }

    return null;
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }

}
