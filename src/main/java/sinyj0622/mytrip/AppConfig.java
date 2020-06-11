package sinyj0622.mytrip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@ComponentScan(value = "sinyj0622.mytrip")
@EnableWebMvc
public class AppConfig {

  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성!");
  }

  @Bean
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer configurer = new TilesConfigurer();
    configurer.setDefinitions("/WEB-INF/defs/tiles.xml");
    return configurer;
  }

  @Bean
  public ViewResolver tilesViewResolver() {
    UrlBasedViewResolver vr = new UrlBasedViewResolver();
    vr.setViewClass(TilesView.class);
    // 뷰 리졸버의 우선순위를 InternalResourceViewResolver보다 우선한다.
    vr.setOrder(1);
    return vr;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
    vr.setOrder(2);
    return vr;
  }


  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver mr = new CommonsMultipartResolver();
    mr.setMaxUploadSize(10000000);
    mr.setMaxInMemorySize(2000000);
    mr.setMaxUploadSizePerFile(5000000);
    return mr;
  }

}
