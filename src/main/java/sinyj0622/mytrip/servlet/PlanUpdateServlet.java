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
      request.setAttribute("update", plan);
      
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/plan/updateform.jsp").include(request, response);
      
      
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
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

      if (planService.update(plan) > 0) {
        response.sendRedirect("list");
      } else {
        throw new Exception("변경 오류");
      }
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
