package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
@MultipartConfig(maxFileSize = 10000000)
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
			request.setAttribute("plan", plan);

			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher("/photoboard/form.jsp").include(request, response);



		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int planNo = Integer.parseInt(request.getParameter("planNo"));
		try {
			ServletContext servletContext = request.getServletContext();
			ApplicationContext iocContainer =
					(ApplicationContext) servletContext.getAttribute("iocContainer");
			PlanService planService = iocContainer.getBean(PlanService.class);
			PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

			Plan plan = planService.get(planNo);
			PhotoBoard photoBoard = new PhotoBoard();
			photoBoard.setTitle(request.getParameter("title"));
			photoBoard.setPlan(plan);

			ArrayList<PhotoFile> photoFiles = new ArrayList<>();
			Collection<Part> parts = request.getParts();
			String dirPath = request.getServletContext().getRealPath("/upload/photoboard");
			for (Part photoPart : parts) {
				if(!photoPart.getName().equals("file") || photoPart.getSize() <= 0) {
					continue;
				} else {
					String filename = UUID.randomUUID().toString();
					photoPart.write(dirPath + "/" + filename);
					photoFiles.add(new PhotoFile().setFilepath(filename));
				}
			}

			if (photoFiles.size() == 0) {
				throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
			}
			photoBoard.setFiles(photoFiles);
			photoBoardService.add(photoBoard);
			response.sendRedirect("list?planNo=" + planNo);


		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.setAttribute("url", "list?planNo=" + planNo);
			request.getRequestDispatcher("/error").forward(request, response);
		}
	}

}

