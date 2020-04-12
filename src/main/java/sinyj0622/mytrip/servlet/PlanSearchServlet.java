package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/plan/search")
public class PlanSearchServlet extends HttpServlet {
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

			request.getRequestDispatcher("/header").include(request, response);
			out.println("  <h1>플랜 검색 결과</h1>");
			out.println("  <a href='list'>목록</a><br>");

			HashMap<String, Object> searchParams = new HashMap<>();
			String keyword = request.getParameter("title");
			if (keyword.length() > 0) {
				searchParams.put("title", keyword);
			}
			keyword = request.getParameter("spot");
			if (keyword.length() > 0) {
				searchParams.put("spot", keyword);
			}

			List<Plan> plans = planService.findByKeyword(searchParams);
			request.setAttribute("search", plans);

			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher("/plan/search.jsp").include(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
