package sinyj0622.mytrip;

import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 객체생성
@Configuration
// 트랜잭션 관리 애노테이션
@EnableTransactionManagement
// Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩하기
@PropertySource("classpath:sinyj0622/mytrip/conf/jdbc.properties")
public class DatabaseConfig {
  static Logger logger = LogManager.getLogger(DatabaseConfig.class);

  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;


  public DatabaseConfig() {
    logger.debug("DatabaseConfig 객체 생성");
  }


  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  @Bean
  public PlatformTransactionManager TransactionManager(
      // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
      // ***단 IoC 컨테이너에 들어 있는 값이어야 한다.***
      DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
