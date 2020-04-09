package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/plan/detail")
public class PlanDetailServlet extends HttpServlet {
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

      int no = Integer.parseInt(request.getParameter("no"));
      Plan p = planService.get(no);

      request.getRequestDispatcher("/header").include(request, response);
      out.println("  <h1>여행 플랜 목록</h1>");
      out.println("  <a href='list'>목록</a><br>");

      if (p != null) {
        out.printf("<p>번호: %d</p>", p.getNo());
        out.printf("<p>여행 주제: %s</p>", p.getTravelTitle());
        out.printf("<p>여행지: %s</p>", p.getDestnation());
        out.printf("<p>여행인원: %s</p>", p.getPerson());
        out.printf("<p>여행 기간: %s ~ %s</p>", p.getStartDate(), p.getEndDate());
        out.printf("<p>예상 경비: %s</p>", p.getTravelMoney());

        out.printf("  <a href='update?no=%d'>변경</a>", p.getNo());
        out.printf("  <a href='delete?no=%d'>삭제</a>", p.getNo());
        out.printf("  <a href='../photoboard/list?planNo=%d'>여행사진첩</a>", p.getNo());
      } else {
        out.println("<p>해당 번호의 게시물이 없습니다.</p>");
      }

      request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
