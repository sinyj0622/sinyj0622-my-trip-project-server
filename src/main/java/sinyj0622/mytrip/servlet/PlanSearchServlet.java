package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PlanSearchServlet {

  PlanService planService;

  public PlanSearchServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/search")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>플랜 검색 결과</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>플랜 검색 결과</h1>");
    out.println("  <a href='/plan/list'>목록</a><br>");

    HashMap<String, Object> searchParams = new HashMap<>();
    String keyword = params.get("title");
    if (keyword.length() > 0) {
      searchParams.put("title", keyword);
    }
    keyword = params.get("spot");
    if (keyword.length() > 0) {
      searchParams.put("spot", keyword);
    }

    List<Plan> plans = planService.findByKeyword(searchParams);
    if (plans == null) {
      out.println("    <p>검색 결과가 없습니다</p>");
    } else {
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>여행 주제</th>");
      out.println("    <th>여행지</th>");
      out.println("    <th>시작일</th>");
      out.println("    <th>종료일</th>");
      out.println("  </tr>");

      for (Plan p : plans) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td>%s</td> "//
            + "<td>%s</td> "//
            + "<td>%s</td> "//
            + "<td>%s</td>"//
            + "</tr>\n", //
            p.getNo(), //
            p.getTravelTitle(), p.getDestnation(), //
            p.getStartDate(), //
            p.getEndDate() //
        );
      }
      out.println("</table>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
