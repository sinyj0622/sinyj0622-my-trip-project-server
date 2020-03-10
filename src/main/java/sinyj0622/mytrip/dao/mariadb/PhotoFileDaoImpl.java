package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.sql.DataSource;

public class PhotoFileDaoImpl implements PhotoFileDao {
	
	SqlSessionFactory sqlSessionFactory;

	  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
	    this.sqlSessionFactory = sqlSessionFactory;
	  }

	@Override
	public int insert(PhotoFile photoFile) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.insert("PhotoFileMapper.insertPhotoFile", photoFile);
	        sqlSession.commit();
	        return count;
	      }
	}

	@Override
	public List<PhotoFile> findAll(int boardNo) throws Exception {
		 try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
		        return sqlSession.selectList("PhotoFileMapper.selectPhotoFile");
		      }
	}

	@Override
	public int deleteAll(int boardNo) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        int count =  sqlSession.delete("PhotoFileMapper.deletePhotoFile", boardNo);
	        sqlSession.commit();
	        return count;
	      }
	}

}
