package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends HttpServlet {
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

      int planNo = Integer.parseInt(request.getParameter("planNo"));
      Plan plan = planService.get(planNo);
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>여행사진 등록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>여행사진 등록</h1>");
      out.println("<form action='add' method='post'>");
      out.printf("플랜번호: <input name='planNo'  type='text' value='%d' readonly><br>\n",
          +plan.getNo());
      out.println("제목: <input name='title'  type='text' ><br>\n");
      out.println("<input name='file1'  type='file' ><br>\n");
      out.println("<input name='file2'  type='file' ><br>\n");
      out.println("<input name='file3'  type='file' ><br>\n");
      out.println("<input name='file4'  type='file' ><br>\n");
      out.println("<input name='file5'  type='file' ><br>\n");
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
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PlanService planService = iocContainer.getBean(PlanService.class);
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int planNo = Integer.parseInt(request.getParameter("planNo"));

      try {
        Plan plan = planService.get(planNo);
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setTitle(request.getParameter("title"));
        photoBoard.setPlan(plan);

        ArrayList<PhotoFile> photoFiles = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
          String filepath = request.getParameter("file" + i);
          if (filepath.length() > 0) {
            photoFiles.add(new PhotoFile().setFilepath(filepath));
          }
        }

        if (photoFiles.size() == 0) {
          throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
        }
        photoBoard.setFiles(photoFiles);
        photoBoardService.add(photoBoard);
        response.sendRedirect("list?planNo=" + planNo);

      } catch (Exception e) {
        request.getSession().setAttribute("errorMessage", "게시글을 등록할 수 없습니다.");
        request.getSession().setAttribute("url", "photoboard/list?planNo=" + planNo);
        response.sendRedirect("../error");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}

