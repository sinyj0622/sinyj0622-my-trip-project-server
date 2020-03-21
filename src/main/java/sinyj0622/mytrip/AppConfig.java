package sinyj0622.mytrip;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan(value = "sinyj0622.mytrip")

//Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩하기
@PropertySource("classpath:sinyj0622/mytrip/conf/jdbc.properties")
// Mybatis DAO 프록시를 자동생성할 인터페이스 경로지정
@MapperScan("sinyj0622.mytrip.dao")
public class AppConfig {

	  @Value("${jdbc.driver}")
	  String jdbcDriver;

	  @Value("${jdbc.url}")
	  String jdbcUrl;

	  @Value("${jdbc.username}")
	  String jdbcUsername;

	  @Value("${jdbc.password}")
	  String jdbcPassword;

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
	  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, // DB 커낵션풀
		      ApplicationContext appCtx) throws Exception {
		  
		  SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		  sqlSessionFactoryBean.setDataSource(dataSource);
		  sqlSessionFactoryBean.setTypeAliasesPackage("sinyj0622.mytrip.domain");
		  sqlSessionFactoryBean.setMapperLocations(
				  appCtx.getResources("classpath:sinyj0622/mytrip/mapper/*Mapper.xml"));
	    return sqlSessionFactoryBean.getObject();
	  }
	  

	  
	  @Bean
	  public PlatformTransactionManager TransactionManager(
	      // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
	      // ***단 IoC 컨테이너에 들어 있는 값이어야 한다.***
			  DataSource dataSource) {
	    return new DataSourceTransactionManager(dataSource);
	  }
	  
	
}
