package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
		out.println("사진 게시글번호? ");
		out.println("!@#");
		out.flush();
		int no = Integer.parseInt(in.nextLine());

    if (photoBoardDao.delete(no) > 0) {
      out.println("삭제하였습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
