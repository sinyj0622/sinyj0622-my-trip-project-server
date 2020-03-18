package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.Component;

@Component("/plan/list")
public class PlanListServlet implements Servlet {

  PlanService planService;

  public PlanListServlet(PlanService planService) {
    this.planService = planService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Plan> plans = planService.list();
    for (Plan p : plans) {
      out.printf("%d, %s, %s, %s ~ %s\n", p.getNo(), p.getTravelTitle(), p.getDestnation(),
          p.getStartDate(), p.getEndDate());
    }
  }

}
