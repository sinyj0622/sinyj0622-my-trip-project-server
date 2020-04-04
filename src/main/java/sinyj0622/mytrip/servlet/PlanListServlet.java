package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PlanListServlet {

  PlanService planService;

  public PlanListServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>여행 플랜 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>여행 플랜 목록</h1>");
    out.println("<form action='/plan/search'>");
    out.println("여행 주제 <input name='title' type='text'><br>\n");
    out.println("여행지 <input name='spot' type='text'>\n");
    out.println("<button>검색</button><br>");
    out.println("  <a href='/plan/addForm'>새 글</a><br>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>여행 주제</th>");
    out.println("    <th>여행지</th>");
    out.println("    <th>시작일</th>");
    out.println("    <th>종료일</th>");
    out.println("  </tr>");

    List<Plan> plans = planService.list();
    for (Plan p : plans) {
      out.printf("  <tr>"//
          + "<td>%d</td> "//
          + "<td><a href='/plan/detail?no=%d'>%s</a></td> "//
          + "<td>%s</td> "//
          + "<td>%s</td> "//
          + "<td>%s</td>"//
          + "</tr>\n", //
          p.getNo(), //
          p.getNo(), //
          p.getTravelTitle(), p.getDestnation(), //
          p.getStartDate(), //
          p.getEndDate() //
      );
    }
    out.println("</table>");

    out.println("</body>");
    out.println("</html>");
  }

}
