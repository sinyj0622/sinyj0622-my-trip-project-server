package sinyj0622.mytrip.dao;

import java.util.List;

import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.PhotoBoard;

public interface PhotoBoardDao {
	int insert(PhotoBoard photoBoard) throws Exception;

	PhotoBoard findByNo(int no) throws Exception;

	int update(PhotoBoard photoBoard) throws Exception;

	int delete(int no) throws Exception;
	
	List<PhotoBoard> findAllByPlanNo(int planNo) throws Exception;

}
