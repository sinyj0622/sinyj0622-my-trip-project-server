package sinyj0622.mytrip.dao;

import java.util.List;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;

public interface PhotoFileDao {
  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;

  int deleteAll(int boardNo) throws Exception;
}
