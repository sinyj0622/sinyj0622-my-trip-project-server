package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;

@Component("/plan/add")
public class PlanAddServlet implements Servlet {

  PlanService planService;

  public PlanAddServlet(PlanService planService) {
    this.planService = planService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Plan plan = new Plan();
    plan.setTravelTitle(Prompt.getString(in, out, "여행 제목? "));
    plan.setDestnation(Prompt.getString(in, out, "여행지? "));
    plan.setPerson(Prompt.getString(in, out, "여행 인원? "));
    plan.setStartDate(Prompt.getString(in, out, "여행 시작일? "));
    plan.setEndDate(Prompt.getString(in, out, "여행 종료일? "));
    plan.setTravelMoney(Prompt.getString(in, out, "예상 경비? "));

    if (planService.add(plan) > 0) {
      out.println("신규 여행일정 등록완료!^^");

    } else {
      out.println("같은 번호의 게시글이 있습니다.");

    }
  }

}
