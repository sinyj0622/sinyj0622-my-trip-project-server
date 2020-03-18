package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class BoardAddServlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Scanner in, PrintStream out) throws Exception {

    Board board = new Board();
    board.setText(Prompt.getString(in, out, "내용: "));

    boardService.add(board);
    out.println("새 게시글을 등록했습니다.");

  }

}
