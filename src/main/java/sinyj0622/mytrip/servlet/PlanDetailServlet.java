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
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/plan/detail")
public class PlanDetailServlet extends HttpServlet {
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

			int no = Integer.parseInt(request.getParameter("no"));
			Plan plan = planService.get(no);
			request.setAttribute("detail", plan);

			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher("/plan/detail.jsp").include(request, response);


		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
