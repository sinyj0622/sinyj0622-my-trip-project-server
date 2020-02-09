package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.BoardObjectDao;
import sinyj0622.mytrip.domain.Board;

public class BoardUpdateServlet implements Servlet {

	BoardObjectDao boardDao;

	public BoardUpdateServlet(BoardObjectDao boardDao) {
		this.boardDao = boardDao;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Board board = (Board) in.readObject();

			 if (boardDao.update(board) > 0) {
			      out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
				out.flush();
			}

	}

}
