package sinyj0622.mytrip;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// @WebListener
public class ContextLoaderListener implements ServletContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {



    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans(iocContainer);
      // 준비할 객체를 담을 바구니 준비
      ServletContext servletContext = sce.getServletContext();
      servletContext.setAttribute("iocContainer", iocContainer);

      // 서블릿 객체는 Spring IoC 컨테이너에서 관리하지 않는다

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void printBeans(ApplicationContext appCtx) {
    logger.debug("Spring IoC 컨테이너에 들어있는 객체들");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      logger.debug(String.format("%s ========= >%s", beanName,
          appCtx.getBean(beanName).getClass().getName()));
    }

  }


  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // 서블릿 컨테이너가 종료되기 직전에 호출
    // 주로 서블릿이 사용한 자원을 해제시키는 코드를 둔다
  }

}
