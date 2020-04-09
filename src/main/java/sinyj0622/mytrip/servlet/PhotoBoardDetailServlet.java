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
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.service.PhotoBoardService;

@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {
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
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      int planNo = Integer.parseInt(request.getParameter("planNo"));
      PhotoBoard photoBoard = photoBoardService.get(no);


      request.getRequestDispatcher("/header").include(request, response);


      out.println("  <h1>여행 사진</h1>");
      out.println("  <a href='list'>목록</a><br>");

      if (photoBoard == null) {
        out.println("<p>사진 없음</p>");
      } else {
        out.println("<form action='update' method='post'>");
        out.printf("플랜번호: <input name='planNo' type='text' readonly value='%d'><br>\n", //
            planNo);
        out.printf("사진번호: <input name='no' type='text' readonly value='%d'><br>\n", //
            photoBoard.getNo());
        out.println("내용:<br>");
        out.printf("<textarea name='title' rows='5' cols='60'>%s</textarea><br>\n", //
            photoBoard.getTitle());
        out.printf("등록일: %s<br>\n", photoBoard.getCreatedDate());
        out.printf("조회수: %d<br>\n", photoBoard.getViewCount());
        out.println("<p>사진파일 </p>\n");
        for (PhotoFile photoFile : photoBoard.getFiles()) {
          out.printf("<p>> %s </p>\n", photoFile.getFilepath());
        }

        out.println("사진: <input name='file1' type='file'><br>");
        out.println("사진: <input name='file2' type='file'><br>");
        out.println("사진: <input name='file3' type='file'><br>");
        out.println("사진: <input name='file4' type='file'><br>");
        out.println("사진: <input name='file5' type='file'><br>");

        out.println("<p><button>변경</button>");
        out.println("</form>");
        out.printf("  <a href='delete?no=%d&planNo=%d'>삭제</a>", photoBoard.getNo(), planNo);

        request.getRequestDispatcher("/footer").include(request, response);

      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
