package sinyj0622.mytrip.service;


import java.util.List;

import sinyj0622.mytrip.domain.PhotoBoard;

public interface PhotoBoardService {

	void add(PhotoBoard photoBoard) throws Exception;

	void delete(int no) throws Exception;

	PhotoBoard get(int no) throws Exception;

	List<PhotoBoard> listPlanPhoto(int no) throws Exception;

	void update(PhotoBoard photoBoard) throws Exception;

}
