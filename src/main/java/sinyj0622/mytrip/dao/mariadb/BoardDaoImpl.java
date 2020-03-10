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
import sinyj0622.mytrip.domain.Board;
import sinyj0622.sql.DataSource;

public class BoardDaoImpl implements BoardDao {

	SqlSessionFactory sqlSessionFactory;

	  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
	    this.sqlSessionFactory = sqlSessionFactory;
	  }

	@Override
	public int insert(Board board) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.insert("BoardMapper.insertBoard", board);
	        sqlSession.commit();
	        return count;
	      }
	}

	@Override
	public List<Board> findAll() throws Exception {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        return sqlSession.selectList("BoardMapper.selectBoard");
	      }
	}

	@Override
	public Board findByNo(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        return sqlSession.selectOne("BoardMapper.detailBoard", no);
	      }
	}

	@Override
	public int update(Board board) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.update("BoardMapper.updateBoard", board);
	        sqlSession.commit();
	        return count;
	      }
	}

	@Override
	public int delete(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.delete("BoardMapper.deleteBoard", no);
	        sqlSession.commit();
	        return count;
	      }
	}

}
