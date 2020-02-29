package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.Plan;

public class PhotoBoardUpdateServlet implements Servlet {

	  PhotoBoardDao photoBoardDao;

	  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
	    this.photoBoardDao = photoBoardDao;
	  }


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		out.println("사진 게시글번호? ");
		out.println("!@#");
		out.flush();
		int no = Integer.parseInt(in.nextLine());

		PhotoBoard old = photoBoardDao.findByNo(no);
		PhotoBoard newPhotoBoard = new PhotoBoard();
		
		if (old == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newPhotoBoard.setNo(old.getNo());
	
		out.printf("제목(%s)? \n", old.getTitle());
		out.println("!@#");		
		newPhotoBoard.setTitle(in.nextLine());
		
		out.flush();

		if (photoBoardDao.update(newPhotoBoard) > 0) { // 업데이트할 게시물을 찾았다면,
			out.println("수정완료!");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}
}
