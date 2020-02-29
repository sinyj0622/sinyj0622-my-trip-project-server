package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.Plan;

public class PhotoBoardListServlet implements Servlet {

  PlanDao planDao;
  PhotoBoardDao photoBoardDao;

  public PhotoBoardListServlet(PlanDao planDao, PhotoBoardDao photoBoardDao) {
    this.planDao = planDao;
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
	  
	  out.println("플랜 번호? ");
	  out.println("!@#");
	  int planNo = Integer.parseInt(in.nextLine());
	  
	 Plan plan = planDao.findByNo(planNo);
	 out.printf("%s\n", plan.getTravelTitle());
	 out.println("---------------------------------------------");
	 out.flush();
	  
   List<PhotoBoard> photoBoards = photoBoardDao.findAllByPlanNo(plan.getNo());
   for (PhotoBoard p : photoBoards) {
       out.printf("%d, %s, %s, %d\n", p.getNo(), p.getTitle(), p.getCreatedDate(), p.getViewCount());
     }
  }

}
