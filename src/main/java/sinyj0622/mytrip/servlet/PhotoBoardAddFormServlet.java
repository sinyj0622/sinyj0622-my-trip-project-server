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

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/photoboard/addForm")
public class PhotoBoardAddFormServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	ServletContext servletContext = req.getServletContext();
	ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");
	PlanService  planService = iocContainer.getBean(PlanService.class);
	
		int planNo = Integer.parseInt(req.getParameter("planNo"));
		Plan plan = planService.get(planNo);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>여행사진 등록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행사진 등록</h1>");
	    out.println("<form action='add'>");
		out.printf("플랜번호: <input name='planNo'  type='text' value='%d' readonly><br>\n", + 
				plan.getNo());
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

}

