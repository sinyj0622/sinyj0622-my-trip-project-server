package sinyj0622.mytrip;

import javax.sql.DataSource;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Mybatis DAO 프록시를 자동생성할 인터페이스 경로지정
@MapperScan("sinyj0622.mytrip.dao")
public class MybatisConfig {
  static Logger logger = LogManager.getLogger(MybatisConfig.class);


  public MybatisConfig() {
    logger.debug("MybatisConfig 객체 생성");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, // DB 커낵션풀
      ApplicationContext appCtx) throws Exception {

	// Mybatis의 log4j2 활성화시키기
	LogFactory.useLog4J2Logging();
	    
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("sinyj0622.mytrip.domain");
    sqlSessionFactoryBean
        .setMapperLocations(appCtx.getResources("classpath:sinyj0622/mytrip/mapper/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject();
  }

}
