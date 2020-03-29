package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PlanAddServlet {

  PlanService planService;

  public PlanAddServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/add")
  public void service(Map<String,String> params, PrintStream out) throws Exception {
    Plan plan = new Plan();
    plan.setTravelTitle(params.get("title"));
    plan.setDestnation(params.get("place"));
    plan.setPerson(params.get("person"));
    plan.setStartDate(params.get("sdt"));
    plan.setEndDate(params.get("edt"));
    plan.setTravelMoney(params.get("money"));
    
    out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
	out.println("<meta charset='UTF-8'>");
	out.println("<title>여행 플랜 작성</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<h1>여행 플랜 작성</h1>");
	out.println("  <a href='/plan/list'>목록</a><br>");
	
    if (planService.add(plan) > 0) {
      out.println("<p>여행 플랜 등록완료</p>");

    } else {
      out.println("<p>등록실패</p>");

    }
    out.println("</body>");
	out.println("</html>");	
  }

}
