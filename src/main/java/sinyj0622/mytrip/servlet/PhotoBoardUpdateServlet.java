package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends GenericServlet {
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
    photoBoard.setTitle(req.getParameter("title"));

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    for (int i = 1; i < 6; i++) {
      String filepath = req.getParameter("file" + i);
      if (filepath.length() > 0) {
        photoFiles.add(new PhotoFile().setFilepath(filepath));
      }
    }

    if (photoFiles.size() > 0) {
      photoBoard.setFiles(photoFiles);
    } else {
      photoBoard.setFiles(null);
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 변경 결과</h1>");

    try {
      photoBoardService.update(photoBoard);
      out.println("<p>사진을 변경했습니다.</p>");
    } catch (Exception e) {
      out.println("<p>해당 사진 게시물이 존재하지 않습니다.</p>");
    }
    out.printf("  <a href='list?planNo=%d'>목록</a>", planNo);
    out.println("</body>");
    out.println("</html>");
	} catch (Exception e) {
		throw new ServletException(e);
	}
  }
}
