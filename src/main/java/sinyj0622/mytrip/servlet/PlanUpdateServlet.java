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

@WebServlet("/plan/update")
public class PlanUpdateServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	ServletContext servletContext = req.getServletContext();
	ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");
	PlanService  planService = iocContainer.getBean(PlanService.class);
	
		Plan plan = new Plan();
		plan.setNo(Integer.parseInt(req.getParameter("no")));
		plan.setTravelTitle(req.getParameter("title"));
		plan.setDestnation(req.getParameter("place"));
		plan.setPerson(req.getParameter("person"));
		plan.setStartDate(req.getParameter("sdt"));
		plan.setEndDate(req.getParameter("edt"));
		plan.setTravelMoney(req.getParameter("money"));
		

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta http-equiv='refresh' content='2;url=list'>");
		out.println("<title>여행 플랜</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행 플랜 작성 결과</h1>");
		
		if (planService.update(plan) > 0) {
			out.println("<p>플랜 변경완료</p>");
		} else {
			out.println("<p>플랜 변경실패</p>");
		}

		out.println("</body>");
		out.println("</html>");	
	} catch (Exception e) {
		throw new ServletException(e);
  }
	}
}
