package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/plan/list")
public class PlanListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PlanService planService = iocContainer.getBean(PlanService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>여행 플랜 목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1>여행 플랜 목록</h1>");
      out.println("<form action='search'>");
      out.println("여행 주제 <input name='title' type='text'><br>\n");
      out.println("여행지 <input name='spot' type='text'>\n");
      out.println("<button>검색</button><br>");
      out.println("  <a href='add'>새 글</a><br>");
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
            + "<td><a href='detail?no=%d'>%s</a></td> "//
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
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
