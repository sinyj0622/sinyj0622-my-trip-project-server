package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao,PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
		out.println("사진 게시글번호? ");
		out.println("!@#");
		out.flush();
		int no = Integer.parseInt(in.nextLine());

    if (photoBoardDao.delete(no) > 0) {
    	photoFileDao.deleteAll(no);
    	
      out.println("삭제하였습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
