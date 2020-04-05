package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@WebServlet("/plan/search")
public class PlanSearchServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	ServletContext servletContext = req.getServletContext();
	ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");
	PlanService  planService = iocContainer.getBean(PlanService.class);
	
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>플랜 검색 결과</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>플랜 검색 결과</h1>");
    out.println("  <a href='list'>목록</a><br>");

    HashMap<String, Object> searchParams = new HashMap<>();
    String keyword = req.getParameter("title");
    if (keyword.length() > 0) {
      searchParams.put("title", keyword);
    }
    keyword = req.getParameter("spot");
    if (keyword.length() > 0) {
      searchParams.put("spot", keyword);
    }

    List<Plan> plans = planService.findByKeyword(searchParams);
    if (plans == null) {
      out.println("    <p>검색 결과가 없습니다</p>");
    } else {
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>여행 주제</th>");
      out.println("    <th>여행지</th>");
      out.println("    <th>시작일</th>");
      out.println("    <th>종료일</th>");
      out.println("  </tr>");

      for (Plan p : plans) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td>%s</td> "//
            + "<td>%s</td> "//
            + "<td>%s</td> "//
            + "<td>%s</td>"//
            + "</tr>\n", //
            p.getNo(), //
            p.getTravelTitle(), p.getDestnation(), //
            p.getStartDate(), //
            p.getEndDate() //
        );
      }
      out.println("</table>");
    }
    out.println("</body>");
    out.println("</html>");
	} catch (Exception e) {
		throw new ServletException(e);
	}
  }

}
