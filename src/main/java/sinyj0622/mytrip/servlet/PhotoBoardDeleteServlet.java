package sinyj0622.mytrip.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.service.PhotoBoardService;

@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int planNo = Integer.parseInt(request.getParameter("planNo"));
    int no = Integer.parseInt(request.getParameter("no"));

    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      photoBoardService.delete(no);
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "list?planNo=" + planNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
