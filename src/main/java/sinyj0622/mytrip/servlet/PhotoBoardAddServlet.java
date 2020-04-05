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
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	ServletContext servletContext = req.getServletContext();
	ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");
	PlanService  planService = iocContainer.getBean(PlanService.class);
	PhotoBoardService  photoBoardService = iocContainer.getBean(PhotoBoardService.class);
	
		int planNo = Integer.parseInt(req.getParameter("planNo"));
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta charset='UTF-8'>");
	    out.println("<meta http-equiv='refresh'" //
	        + " content='2;url=list?planNo=" + planNo + "'>");
	    out.println("<title>사진 입력</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>사진 입력 결과</h1>");

		try {
			Plan plan = planService.get(planNo);
			PhotoBoard photoBoard = new PhotoBoard();
			photoBoard.setTitle(req.getParameter("title"));
			photoBoard.setPlan(plan);

			ArrayList<PhotoFile> photoFiles = new ArrayList<>();
			for (int i = 1; i < 6; i++) {
				String filepath = req.getParameter("file" + i);
				if (filepath.length() > 0) {
					photoFiles.add(new PhotoFile().setFilepath(filepath));
				}
			}


			if (photoFiles.size() == 0) {
				throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
			}
			photoBoard.setFiles(photoFiles);

			photoBoardService.add(photoBoard);
			out.println("<p>사진 게시글을 등록했습니다!</p>");
		}catch (Exception e) {
			out.printf("<p>%s</p>\n", e.getMessage());

		}
		out.println("</body>");
		out.println("</html>");
	} catch (Exception e) {
	      throw new ServletException(e);
	}
	}

}

