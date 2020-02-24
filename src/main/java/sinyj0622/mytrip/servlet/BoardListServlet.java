package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;

public class BoardListServlet implements Servlet {

  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
	  
    List<Board> boards = boardDao.findAll();
    for (Board b : boards) {
      out.printf("%d, %s, %s, %d\n", b.getNo(), b.getText(), b.getDate(),
          b.getViewCount());
    }

  }

}
