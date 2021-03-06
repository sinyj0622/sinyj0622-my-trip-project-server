package sinyj0622.mytrip.dao;

import java.util.List;
import sinyj0622.mytrip.domain.Board;

public interface BoardDao {

  public void insert(Board board) throws Exception;

  public List<Board> findAll() throws Exception;

  public Board findByNo(int no) throws Exception;

  public int update(Board board) throws Exception;

  public int delete(int no) throws Exception;

}
