package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.domain.PhotoBoard;

public class PhotoBoardDetailServlet implements Servlet {

	  PhotoBoardDao photoBoardDao;

	  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao) {
	    this.photoBoardDao = photoBoardDao;
	  }

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		out.println("번호? ");
		out.println("!@#");
		out.flush();
		int no = Integer.parseInt(in.nextLine());

		PhotoBoard p = photoBoardDao.findByNo(no);
		if (p != null) {
			out.printf("번호: %d\n", p.getNo());
			out.printf("제목: %s\n", p.getTitle());
			out.printf("등록일: %s\n", p.getCreatedDate());
			out.printf("조회수: %d\n", p.getViewCount());
			out.flush();
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}

}
