package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.Plan;

public class PhotoBoardAddServlet implements Servlet {

	PlanDao planDao;
	PhotoBoardDao photoBoardDao;

	public PhotoBoardAddServlet(PlanDao planDao, PhotoBoardDao photoBoardDao) {
		this.planDao = planDao;
		this.photoBoardDao = photoBoardDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		out.println("플랜 번호? ");
		out.println("!@#");
		int planNo = Integer.parseInt(in.nextLine());

		Plan plan = planDao.findByNo(planNo);

		PhotoBoard photoBoard = new PhotoBoard();
		out.println("내용? ");
		out.println("!@#");
		String title = in.nextLine();	
		photoBoard.setTitle(title);
		photoBoard.setPlan(plan);


		if (photoBoardDao.insert(photoBoard) > 0) {
			out.println("등록완료!");

		} else {
			out.println("같은 번호의 게시글이 있습니다.");

		}
	}

}
