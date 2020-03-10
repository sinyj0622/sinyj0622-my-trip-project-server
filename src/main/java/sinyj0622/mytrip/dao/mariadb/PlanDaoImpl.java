package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.sql.DataSource;

public class PlanDaoImpl implements PlanDao {

	SqlSessionFactory sqlSessionFactory;

	  public PlanDaoImpl(SqlSessionFactory sqlSessionFactory) {
	    this.sqlSessionFactory = sqlSessionFactory;
	  }

	@Override
	public int insert(Plan plan) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.insert("PlanMapper.insertPlan", plan);
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
	        int count =  sqlSession.update("PlanMapper.updatePlan", plan);
	        sqlSession.commit();
	        return count;
	      }
	}

	@Override
	public int delete(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.update("PlanMapper.deletePlan", no);
	        sqlSession.commit();
	        return count;
	      }
	}


}
