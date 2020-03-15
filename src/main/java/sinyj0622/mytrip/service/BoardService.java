package sinyj0622.mytrip.service;

import java.util.List;

import sinyj0622.mytrip.domain.Board;

public interface BoardService {

	void add(Board board) throws Exception;

	int delete(int no) throws Exception;

	Board get(int no) throws Exception;

	List<Board> list() throws Exception;

	int update(Board newBoard) throws Exception;



}
