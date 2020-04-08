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

@WebServlet("/plan/add")
public class PlanAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>여행 플랜 작성</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>여행 플랜 작성</h1>");
      out.println("<form action='add' method='post'>");
      out.println("여행 주제: <input name='title'  type='text' ><br>\n");
      out.println("여행지: <input name='place'  type='text' ><br>\n");
      out.println("여행인원: <input name='person'  type='number' >명<br>\n");
      out.println("시작일: <input name='sdt'  type='date' ><br>\n");
      out.println("종료일: <input name='edt'  type='date' ><br>\n");
      out.println("예상 경비: <input name='money'  type='number'>만원<br>\n");
      out.println("<button>등록</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PlanService planService = iocContainer.getBean(PlanService.class);

      Plan plan = new Plan();
      plan.setTravelTitle(request.getParameter("title"));
      plan.setDestnation(request.getParameter("place"));
      plan.setPerson(request.getParameter("person"));
      plan.setStartDate(request.getParameter("sdt"));
      plan.setEndDate(request.getParameter("edt"));
      plan.setTravelMoney(request.getParameter("money"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>여행 플랜 작성</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>여행 플랜 작성</h1>");
      out.println("  <a href='list'>목록</a><br>");

      if (planService.add(plan) > 0) {
        out.println("<p>여행 플랜 등록완료</p>");

      } else {
        out.println("<p>등록실패</p>");

      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
