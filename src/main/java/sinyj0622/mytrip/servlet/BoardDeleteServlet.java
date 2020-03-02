package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.util.Prompt;

public class BoardDeleteServlet implements Servlet {

	BoardDao boardDao;

	public BoardDeleteServlet(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		
	    int no = Prompt.getInt(in, out, "번호? ");

		if (boardDao.delete(no) > 0) {
			out.println("해당 번호의 게시물을 삭제하였습니다.");

		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}

}
