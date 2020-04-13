package sinyj0622.mytrip.servlet;

import java.io.IOException;
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
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PlanService planService = iocContainer.getBean(PlanService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      planService.delete(no);
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", new Exception("사진게시글이 존재하여 삭제할 수 없습니다."));
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

}
