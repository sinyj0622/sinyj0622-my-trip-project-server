package sinyj0622.mytrip.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;

public class BoardServiceImpl2 implements BoardService {

	SqlSessionFactory sqlSessionFactory;

	public BoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public void add(Board board) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
			 boardDao.insert(board);
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		return boardDao.delete(no);
		}
	}

	@Override
	public Board get(int no) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		return boardDao.findByNo(no);
		}
	}

	@Override
	public List<Board> list() throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		return boardDao.findAll();
		}
	}

	@Override
	public int update(Board newBoard) throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		return boardDao.update(newBoard);
		}
	}

}
