package sinyj0622.sql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class SqlSessionProxy implements SqlSession {

  SqlSession origin;

  public SqlSessionProxy(SqlSession origin) {
    this.origin = origin;
  }


  public void realClose() {
    origin.close();
  }

  @Override
  public void close() {
    // origin.close();
  }

  @Override
  public <T> T selectOne(String statement) {
    return origin.selectOne(statement);
  }

  @Override
  public <T> T selectOne(String statement, Object parameter) {
    return origin.selectOne(statement, parameter);
  }

  @Override
  public <E> List<E> selectList(String statement) {
    return origin.selectList(statement);
  }

  @Override
  public <E> List<E> selectList(String statement, Object parameter) {
    return origin.selectList(statement, parameter);
  }

  @Override
  public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
    return origin.selectList(statement, parameter, rowBounds);
  }

  @Override
  public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
    return origin.selectMap(statement, mapKey);
  }

  @Override
  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
    return origin.selectMap(statement, parameter, mapKey);
  }

  @Override
  public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey,
      RowBounds rowBounds) {
    return origin.selectMap(statement, parameter, mapKey, rowBounds);
  }

  @Override
  public <T> Cursor<T> selectCursor(String statement) {
    return origin.selectCursor(statement);
  }

  @Override
  public <T> Cursor<T> selectCursor(String statement, Object parameter) {
    return origin.selectCursor(statement, parameter);
  }

  @Override
  public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
    return origin.selectCursor(statement, parameter, rowBounds);
  }

  @Override
  public void select(String statement, Object parameter, ResultHandler handler) {
    origin.select(statement, parameter, handler);
  }

  @Override
  public void select(String statement, ResultHandler handler) {
    origin.select(statement, handler);
  }

  @Override
  public void select(String statement, Object parameter, RowBounds rowBounds,
      ResultHandler handler) {
    origin.select(statement, parameter, rowBounds, handler);
  }

  @Override
  public int insert(String statement) {
    return origin.insert(statement);
  }

  @Override
  public int insert(String statement, Object parameter) {
    return origin.insert(statement, parameter);
  }

  @Override
  public int update(String statement) {
    return origin.update(statement);
  }

  @Override
  public int update(String statement, Object parameter) {
    return origin.update(statement, parameter);
  }

  @Override
  public int delete(String statement) {
    return origin.delete(statement);
  }

  @Override
  public int delete(String statement, Object parameter) {
    return origin.delete(statement, parameter);
  }

  @Override
  public void commit() {
    origin.commit();
  }

  @Override
  public void commit(boolean force) {
    origin.commit(force);
  }

  @Override
  public void rollback() {
    origin.rollback();
  }

  @Override
  public void rollback(boolean force) {
    origin.rollback(force);
  }

  @Override
  public List<BatchResult> flushStatements() {
    return origin.flushStatements();
  }



  @Override
  public void clearCache() {
    origin.clearCache();
  }

  @Override
  public Configuration getConfiguration() {
    return origin.getConfiguration();
  }

  @Override
  public <T> T getMapper(Class<T> type) {
    return origin.getMapper(type);
  }

  @Override
  public Connection getConnection() {
    return origin.getConnection();
  }


}
