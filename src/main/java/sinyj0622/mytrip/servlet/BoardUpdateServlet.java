package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;

public class BoardUpdateServlet implements Servlet {

	BoardDao boardDao;

	public BoardUpdateServlet(BoardDao boardDao) {
		this.boardDao = boardDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {

		out.println("번호? ");
		out.println("!@#");
		int no = Integer.parseInt(in.nextLine());
		
		Board oldBoard = boardDao.findByNo(no);
		Board newBoard = new Board();
		
		if (oldBoard == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}
		
		newBoard.setNo(oldBoard.getNo());
		out.printf("내용(%s):\n",oldBoard.getText());
		out.println("!@#");
		newBoard.setText(in.nextLine());
	    out.flush();
		
		if (boardDao.update(newBoard) > 0) {
			out.println("게시글을 수정하였습니다.");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
			
		}

	}

}
