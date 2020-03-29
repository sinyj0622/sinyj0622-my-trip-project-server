package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PlanDetailServlet {

	PlanService planService;

	public PlanDetailServlet(PlanService planService) {
		this.planService = planService;
	}

	@RequestMapping("/plan/detail")
	public void service(Map<String,String> params, PrintStream out) throws Exception {
		int no = Integer.parseInt(params.get("no"));
		Plan p = planService.get(no);

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("  <meta charset='UTF-8'>");
		out.println("  <title>여행 플랜 목록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("  <h1>여행 플랜 목록</h1>");
		out.println("  <a href='/plan/list'>목록</a><br>");

		if (p != null) {
			out.printf("<p>번호: %d</p>", p.getNo());
			out.printf("<p>여행 주제: %s</p>", p.getTravelTitle());
			out.printf("<p>여행지: %s</p>", p.getDestnation());
			out.printf("<p>여행인원: %s</p>", p.getPerson());
			out.printf("<p>여행 기간: %s ~ %s</p>", p.getStartDate(), p.getEndDate());
			out.printf("<p>예상 경비: %s</p>", p.getTravelMoney());

			out.printf("  <a href='/plan/updateForm?no=%d'>변경</a>",p.getNo());
			out.printf("  <a href='/plan/delete?no=%d'>삭제</a>",p.getNo());
			out.printf("  <a href='/photoboard/list?planNo=%d'>여행사진첩</a>", p.getNo());
		} else {
			out.println("<p>해당 번호의 게시물이 없습니다.</p>");
		}		
		out.println("</body>");
		out.println("</html>");
		}
}
