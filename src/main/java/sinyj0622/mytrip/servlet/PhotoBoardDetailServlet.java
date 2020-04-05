package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.service.PhotoBoardService;

@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	ServletContext servletContext = req.getServletContext();
	ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");
	PhotoBoardService  photoBoardService = iocContainer.getBean(PhotoBoardService.class);
	
    int no = Integer.parseInt(req.getParameter("no"));
    int planNo = Integer.parseInt(req.getParameter("planNo"));
    PhotoBoard photoBoard = photoBoardService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>여행 사진</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>여행 사진</h1>");
    out.println("  <a href='list'>목록</a><br>");

    if (photoBoard == null) {
      out.println("<p>사진 없음</p>");
    } else {
      out.println("<form action='update'>");
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
      out.printf("  <a href='delete?no=%d&planNo=%d'>삭제</a>", photoBoard.getNo(),
          planNo);
      out.println("</body>");
      out.println("</html>");
	}
	} catch (Exception e) {
		throw new ServletException(e);
	}
	}
}
