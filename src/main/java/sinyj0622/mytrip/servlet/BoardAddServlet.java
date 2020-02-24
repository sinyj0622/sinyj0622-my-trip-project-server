package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;

public class BoardAddServlet implements Servlet {

	BoardDao boardDao;

	public BoardAddServlet(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {

		Board board = new Board();
		out.println("내용: ");
		out.println("!@#");
	    out.flush();
		board.setText(in.nextLine());

	    if (boardDao.insert(board) > 0) {
	        out.println("새 게시글을 등록했습니다.");

	      } else {
	        out.println("게시글 등록에 실패했습니다.");
	      }
		}
	
}
