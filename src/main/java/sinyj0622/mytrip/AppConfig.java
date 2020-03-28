package sinyj0622.mytrip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "sinyj0622.mytrip")
public class AppConfig {

  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성!");
  }


}
