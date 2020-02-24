package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanListServlet implements Servlet {

  PlanDao planDao;

  public PlanListServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
   List<Plan> plans = planDao.findAll();
   for (Plan p : plans) {
       out.printf("%d, %s, %s, %s ~ %s\n", p.getNo(), p.getTravelTitle(), p.getDestnation(),
           p.getStartDate(), p.getEndDate());
     }
  }

}
