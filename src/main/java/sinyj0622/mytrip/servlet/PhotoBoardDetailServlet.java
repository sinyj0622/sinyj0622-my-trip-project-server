package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
			request.setAttribute("detail", photoBoard);
			request.setAttribute("planNo", planNo);

			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher("/photoboard/detail.jsp").include(request, response);


		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
