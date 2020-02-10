package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.BoardObjectFileDao;
import sinyj0622.mytrip.domain.Board;

public class BoardListServlet implements Servlet {

	BoardObjectFileDao boardDao;

	public BoardListServlet(BoardObjectFileDao boardDao) {
		this.boardDao = boardDao;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
		out.writeUTF("OK");
		out.reset();
		out.writeObject(boardDao.findAll());
	}

}
