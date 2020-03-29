package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;


import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class PlanUpdateServlet {

	PlanService planService;

	public PlanUpdateServlet(PlanService planService) {
		this.planService = planService;
	}


	@RequestMapping("/plan/update")
	public void service(Map<String,String> params, PrintStream out) throws Exception {
		Plan plan = new Plan();
		plan.setNo(Integer.parseInt(params.get("no")));
		plan.setTravelTitle(params.get("title"));
		plan.setDestnation(params.get("place"));
		plan.setPerson(params.get("person"));
		plan.setStartDate(params.get("sdt"));
		plan.setEndDate(params.get("edt"));
		plan.setTravelMoney(params.get("money"));
		

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta http-equiv='refresh' content='2;url=/plan/list'>");
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
	}
}
