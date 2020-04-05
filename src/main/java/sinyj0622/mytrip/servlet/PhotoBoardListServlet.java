package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.service.PhotoBoardService;

@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	ServletContext servletContext = req.getServletContext();
	ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");
	PhotoBoardService  photoBoardService = iocContainer.getBean(PhotoBoardService.class);
	
	  int planNo = Integer.parseInt(req.getParameter("planNo"));

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("  <meta charset='UTF-8'>");
		out.println("  <title>여행 사진 목록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("  <h1>여행 사진 목록</h1>");
		out.printf("  <a href='addForm?planNo=%d'>새 사진</a><br>", planNo);
		out.println("  <table border='1'>");
		out.println("  <tr>");
		out.println("    <th>번호</th>");
		out.println("    <th>제목</th>");
		out.println("    <th>등록일</th>");
		out.println("    <th>조회수</th>");
		out.println("  </tr>");

		List<PhotoBoard> photoBoards = photoBoardService.listPlanPhoto(planNo);
		for (PhotoBoard photoBoard : photoBoards) {
			out.printf("  <tr>"//
					+ "<td>%d</td> "//
					+ "<td><a href='detail?no=%d&planNo=%d'>%s</a></td> "//
					+ "<td>%s</td> "//
					+ "<td>%d</td> "//
					+ "</tr>\n", //
					photoBoard.getNo(),
					photoBoard.getNo(),
					planNo,//
					photoBoard.getTitle(),
					photoBoard.getCreatedDate(),
					photoBoard.getViewCount()
					);
		}  out.println("</table>");

		out.println("</body>");
		out.println("</html>");
	} catch (Exception e) {
		throw new ServletException(e);
	}
	}

}
