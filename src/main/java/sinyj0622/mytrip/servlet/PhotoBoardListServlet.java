package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PhotoBoardListServlet {

  PlanService planService;
  PhotoBoardService photoBoardService;

  public PhotoBoardListServlet(PlanService planService, PhotoBoardService photoBoardService) {
    this.planService = planService;
    this.photoBoardService = photoBoardService;
  }



  @RequestMapping("/photoboard/list")
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("플랜 번호? ");
    out.println("!@#");
    int planNo = Integer.parseInt(in.nextLine());

    Plan plan = planService.get(planNo);

    if (plan == null) {
      out.println("해당 번호의 플랜이 없습니다");
      out.flush();
      return;
    }

    out.printf("%s\n", plan.getTravelTitle());
    out.println("---------------------------------------------");
    out.flush();

    List<PhotoBoard> photoBoards = photoBoardService.listPlanPhoto(plan.getNo());
    for (PhotoBoard p : photoBoards) {
      out.printf("%d, %s, %s, %d\n", p.getNo(), p.getTitle(), p.getCreatedDate(), p.getViewCount());
    }
  }

}
