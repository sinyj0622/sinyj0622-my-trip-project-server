package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;

@Component("/board/detail")
public class BoardDetailServlet implements Servlet {

  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    Board board = boardService.get(no);

    if (board != null) {
      out.printf("번호: %d\n", board.getNo());
      out.printf("내용: %s\n", board.getText());
      out.printf("등록일: %s\n", board.getDate());
      out.printf("조회수: %d\n", board.getViewCount());

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
