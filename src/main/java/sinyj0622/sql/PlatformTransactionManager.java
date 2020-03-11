package sinyj0622.sql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PlatformTransactionManager {

  SqlSessionFactory sqlSessionFactory;

  public PlatformTransactionManager(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public void beginTransaction() throws Exception {
    // 기존에 존재하는 sqlSession 객체를 지운다
    ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
    // 수동커밋으로 변경
    sqlSessionFactory.openSession(false);

  }

  public void commit() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.commit();
  }

  public void rollback() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.rollback();
  }

}
