package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;
import sinyj0622.util.Prompt;

public class BoardAddServlet implements Servlet {

	BoardService boardService;

	public BoardAddServlet(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {

		Board board = new Board();
		board.setText(Prompt.getString(in, out, "내용: "));

		boardService.add(board);
		out.println("새 게시글을 등록했습니다.");

	}

}
