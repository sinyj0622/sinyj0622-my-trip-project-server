package sinyj0622.mytrip.service.Impl;

import java.util.List;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;

public class BoardServiceImpl implements BoardService {
	
	BoardDao boardDao;
	
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public int add(Board board) throws Exception {
		return boardDao.insert(board);
	}

	@Override
	public int delete(int no) throws Exception {
		return boardDao.delete(no);
	}

	@Override
	public Board get(int no) throws Exception {
		return boardDao.findByNo(no);
	}

	@Override
	public List<Board> list() throws Exception {
		return boardDao.findAll();
	}

	@Override
	public int update(Board newBoard) throws Exception {
		return boardDao.update(newBoard);
	}

}
