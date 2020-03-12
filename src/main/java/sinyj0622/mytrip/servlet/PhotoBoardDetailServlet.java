package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.Prompt;

public class PhotoBoardDetailServlet implements Servlet {

	  PhotoBoardService photoBoardService;

	  public PhotoBoardDetailServlet(PhotoBoardService photoBoardService) {
	    this.photoBoardService = photoBoardService;
	  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    PhotoBoard photoBoard = photoBoardService.get(no);
    if (photoBoard != null) {
      out.printf("번호: %d\n", photoBoard.getNo());
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("등록일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());
      
      out.println("사진파일: ");
      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("> %s\n", photoFile);
        out.flush();
      }


    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
