package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PlanDeleteServlet {

  PlanService planService;
  
  public PlanDeleteServlet(PlanService planService,PhotoBoardService photoBoardService) {
    this.planService = planService;
  }


  @RequestMapping("/plan/delete")
  public void service(Map<String,String> params, PrintStream out) throws Exception {
		int no = Integer.parseInt(params.get("no"));
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.printf("<meta http-equiv='refresh' content='2;url=/plan/list'>");
		out.println("<title>여행 플랜</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행 플랜 삭제결과</h1>");
    if (planService.delete(no) > 0) {
      out.println("<p>플랜 삭제 완료!</p>");
    } else {
      out.println("<p>플랜 삭제 실패</p>");
    }

	out.println("</body>");
	out.println("</html>");	
  }

}
