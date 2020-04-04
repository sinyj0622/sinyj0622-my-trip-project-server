package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PlanUpdateFormServlet {

	PlanService planService;

	public PlanUpdateFormServlet(PlanService planService) {
		this.planService = planService;
	}


	@RequestMapping("/plan/updateForm")
	public void service(Map<String,String> params, PrintWriter out) throws Exception {
		int no = Integer.parseInt(params.get("no"));
		Plan plan = planService.get(no);

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>여행 플랜 작성</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행 플랜 작성</h1>");


		if (plan == null) {
			out.println("<p>해당 번호의 플랜이 없습니다.</p>");
		} else {
			out.println("<form action='/plan/update'>"); 
			out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n",plan.getNo());
			out.printf("여행 주제: <input name='title'  type='text' value=%s><br>\n", plan.getTravelTitle());
			out.printf("여행지: <input name='place'  type='text' value=%s><br>\n", plan.getDestnation());
			out.printf("여행인원: <input name='person'  type='number' value=%s>명<br>\n", plan.getPerson());
			out.printf("시작일: <input name='sdt'  type='date' value=%s><br>\n", plan.getStartDate());
			out.printf("종료일: <input name='edt'  type='date' value=%s><br>\n", plan.getEndDate());
			out.printf("예상 경비: <input name='money' type='number' value=%s>만원<br>\n", plan.getTravelMoney());
			out.printf("<button>수정</button>");
		}

		out.println("</body>");
		out.println("</html>");	
	}
}
