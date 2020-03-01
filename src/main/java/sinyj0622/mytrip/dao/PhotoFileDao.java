package sinyj0622.mytrip.dao;

import java.util.List;

import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;

public interface PhotoFileDao {
	int insert(PhotoFile photoFile) throws Exception;

	PhotoFile findByNo(int no) throws Exception;

	int update(PhotoFile photoFile) throws Exception;

	int delete(int no) throws Exception;
	
	List<PhotoFile> findAllByPlanNo(int planNo) throws Exception;

}
