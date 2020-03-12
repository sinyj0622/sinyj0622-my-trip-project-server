package sinyj0622.mytrip.dao.mariadb;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanDaoImpl implements PlanDao {

  SqlSessionFactory sqlSessionFactory;

  public PlanDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Plan plan) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("PlanMapper.insertPlan", plan);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<Plan> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PlanMapper.selectPlan");
    }
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("PlanMapper.detailPlan", no);
    }
  }

  @Override
  public int update(Plan plan) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("PlanMapper.updatePlan", plan);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("PlanMapper.deletePlan", no);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<Plan> findByKeyword(Map<String, Object> params) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PlanMapper.selectPlan", params);
    }
  }

}
