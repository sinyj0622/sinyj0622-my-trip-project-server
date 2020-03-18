package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;
import sinyj0622.util.Component;

@Component("/board/list")
public class BoardListServlet implements Servlet {

  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Board> boards = boardService.list();
    for (Board b : boards) {
      out.printf("%d, %s, %s, %d\n", b.getNo(), b.getText(), b.getDate(), b.getViewCount());
    }

  }

}
