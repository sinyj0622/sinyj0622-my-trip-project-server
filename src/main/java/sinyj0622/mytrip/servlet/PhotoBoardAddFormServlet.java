package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PhotoBoardAddFormServlet {
	  PlanService planService;

	  public PhotoBoardAddFormServlet( PlanService planService) {
	    this.planService = planService;
	  }

  @RequestMapping("/photoboard/addForm")
  public void service(Map<String,String> params, PrintWriter out) throws Exception {
		int planNo = Integer.parseInt(params.get("planNo"));
		Plan plan = planService.get(planNo);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>여행사진 등록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행사진 등록</h1>");
	    out.println("<form action='/photoboard/add'>");
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
  }

}

