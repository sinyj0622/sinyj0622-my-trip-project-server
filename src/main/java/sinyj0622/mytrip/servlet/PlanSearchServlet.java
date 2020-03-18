package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;

@Component("/plan/search")
public class PlanSearchServlet implements Servlet {

  PlanService planService;

  public PlanSearchServlet(PlanService planService) {
    this.planService = planService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    HashMap<String, Object> params = new HashMap<>();
    String keyword = Prompt.getString(in, out, "여행 제목 검색: ");
    if (keyword.length() > 0) {
      params.put("title", keyword);
    }
    keyword = Prompt.getString(in, out, "여행지 검색: ");
    if (keyword.length() > 0) {
      params.put("spot", keyword);
    }


    out.println("------------------------------");
    out.println("[검색 결과]");
    List<Plan> plans = planService.findByKeyword(params);
    for (Plan p : plans) {
      out.printf("%d, %s, %s, %s ~ %s\n", p.getNo(), p.getTravelTitle(), p.getDestnation(),
          p.getStartDate(), p.getEndDate());
    }
  }

}
