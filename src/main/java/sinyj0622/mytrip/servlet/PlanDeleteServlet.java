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
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/plan/delete")
public class PlanDeleteServlet extends HttpServlet {
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

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>여행 플랜</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>여행 플랜 삭제완료</h1>");
      if (planService.delete(no) > 0) {
        out.println("<p>플랜 삭제 완료!</p>");
      } else {
        out.println("<p>플랜 삭제 실패</p>");
      }

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
