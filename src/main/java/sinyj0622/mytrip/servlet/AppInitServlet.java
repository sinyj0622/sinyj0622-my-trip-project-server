package sinyj0622.mytrip.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sinyj0622.mytrip.AppConfig;
import sinyj0622.mytrip.ContextLoaderListener;

@WebServlet(value = "/AppInitServlet", loadOnStartup = 1)
public class AppInitServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void init() throws ServletException {
    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans(iocContainer);
      // 준비할 객체를 담을 바구니 준비
      ServletContext servletContext = getServletContext();
      servletContext.setAttribute("iocContainer", iocContainer);

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
}

