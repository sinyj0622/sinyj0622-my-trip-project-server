package sinyj0622.mytrip;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.sql.MybatisDaoFactory;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.SqlSessionFactoryProxy;
import sinyj0622.util.RequestHandler;
import sinyj0622.util.RequestMapping;
import sinyj0622.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {
  // 다른 클래스에서 커넥션을 사용할 수 있도록 공개
  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans(appCtx);

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
  
  private void printBeans(ApplicationContext appCtx) {
	    System.out.println("Spring IoC 컨테이너에 들어있는 객체들");
	    String[] beanNames = appCtx.getBeanDefinitionNames();
	    for (String beanName : beanNames) {
	      System.out.printf("%s ========= >%s\n", beanName,
	          appCtx.getBean(beanName).getClass().getName());
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
