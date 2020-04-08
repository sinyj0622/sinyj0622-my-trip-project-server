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

@WebServlet("/plan/update")
public class PlanUpdateServlet extends HttpServlet {
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
      Plan plan = planService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>여행 플랜 작성</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>여행 플랜 작성</h1>");


      if (plan == null) {
        out.println("<p>해당 번호의 플랜이 없습니다.</p>");
      } else {
        out.println("<form action='update' method='post'>");
        out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", plan.getNo());
        out.printf("여행 주제: <input name='title'  type='text' value=%s><br>\n",
            plan.getTravelTitle());
        out.printf("여행지: <input name='place'  type='text' value=%s><br>\n", plan.getDestnation());
        out.printf("여행인원: <input name='person'  type='number' value=%s>명<br>\n", plan.getPerson());
        out.printf("시작일: <input name='sdt'  type='date' value=%s><br>\n", plan.getStartDate());
        out.printf("종료일: <input name='edt'  type='date' value=%s><br>\n", plan.getEndDate());
        out.printf("예상 경비: <input name='money' type='number' value=%s>만원<br>\n",
            plan.getTravelMoney());
        out.printf("<button>수정</button>");
      }

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
      plan.setNo(Integer.parseInt(request.getParameter("no")));
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
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>여행 플랜</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>여행 플랜 작성 결과</h1>");

      if (planService.update(plan) > 0) {
        out.println("<p>플랜 변경완료</p>");
      } else {
        out.println("<p>플랜 변경실패</p>");
      }

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
