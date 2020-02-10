package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.BoardObjectFileDao;
import sinyj0622.mytrip.domain.Board;

public class BoardDeleteServlet implements Servlet {

	BoardObjectFileDao boardDao;

	public BoardDeleteServlet(BoardObjectFileDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
		int no = in.readInt();

		if (boardDao.delete(no) > 0) {
			out.writeUTF("OK");

		} else {
			out.writeUTF("FAIL");
			out.writeUTF("해당 번호의 게시물이 없습니다.");
		}
	}

}
