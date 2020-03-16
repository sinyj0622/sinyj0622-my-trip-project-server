package sinyj0622.sql;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {

  SqlSessionFactory originalFactory;

  ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

  public SqlSessionFactoryProxy(SqlSessionFactory originalFactory) {
    this.originalFactory = originalFactory;
  }

  public void closeSession() {
    SqlSession sqlSession = threadLocal.get(); // 스레드에서 꺼낸다

    if (sqlSession != null) { // 꺼낸 객체가 있다면
      threadLocal.remove(); // 제거
      ((SqlSessionProxy) sqlSession).realClose(); // 진짜로 닫는다
    }

  }

  @Override
  public SqlSession openSession() {
    return this.openSession(true);
  }

  @Override
  public SqlSession openSession(boolean autoCommit) {
    // 스레드에 보관된것을 꺼낸다
    SqlSession sqlSession = threadLocal.get();
    // 스레드에 보관된 객체가 없다면 생성한다
    if (sqlSession == null) {
      sqlSession = new SqlSessionProxy(originalFactory.openSession(autoCommit));
      // 스레드에 보관한다
      threadLocal.set(sqlSession);
    }

    return sqlSession;
  }

  @Override
  public SqlSession openSession(Connection connection) {
    return originalFactory.openSession(connection);
  }

  @Override
  public SqlSession openSession(TransactionIsolationLevel level) {
    return originalFactory.openSession(level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType) {
    return originalFactory.openSession(execType);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return originalFactory.openSession(execType, autoCommit);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return originalFactory.openSession(execType, level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return originalFactory.openSession(execType, connection);
  }

  @Override
  public Configuration getConfiguration() {
    return originalFactory.getConfiguration();
  }


}
