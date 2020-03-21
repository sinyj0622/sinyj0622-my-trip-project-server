package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class PlanDeleteServlet {

  PlanService planService;

  public PlanDeleteServlet(PlanService planService) {
    this.planService = planService;
  }


  @RequestMapping("/plan/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (planService.delete(no) > 0) {
      out.println("해당 여행일정을 삭제하였습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }

}
