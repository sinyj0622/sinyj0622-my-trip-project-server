package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sinyj0622.mytrip.dao.json.BoardJsonFileDao;
import sinyj0622.mytrip.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardJsonFileDao boardDao;

  public BoardAddServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    if (boardDao.insert(board) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");

    }

  }

}
