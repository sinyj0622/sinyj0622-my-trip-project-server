package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.TransactionTemplate;
import sinyj0622.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

	  PhotoBoardService photoBoardService;

	  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
	    this.photoBoardService = photoBoardService;
	  }



  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "사진 게시글번호? ");

    photoBoardService.delete(no);
    out.println("삭제하였습니다.");
  }

}
