package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.util.Prompt;

public class BoardUpdateServlet implements Servlet {

	BoardDao boardDao;

	public BoardUpdateServlet(BoardDao boardDao) {
		this.boardDao = boardDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "번호? ");
		
		Board oldBoard = boardDao.findByNo(no);
		Board newBoard = new Board();
		
		if (oldBoard == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}
		
		newBoard.setNo(oldBoard.getNo());
		newBoard.setText(Prompt.getString(in, out,
				String.format("내용(%s):", oldBoard.getText()), oldBoard.getText()));
		
		if (boardDao.update(newBoard) > 0) {
			out.println("게시글을 수정하였습니다.");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
			
		}

	}

}
