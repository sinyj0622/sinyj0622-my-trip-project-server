package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.util.RequestMapping;

@Component
public class PlanAddFormServlet {


  @RequestMapping("/plan/addForm")
  public void service(Map<String,String> params, PrintStream out) throws Exception {
	  	out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>여행 플랜 작성</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행 플랜 작성</h1>");
		out.println("<form action='/plan/add'>"); 
		out.println("여행 주제: <input name='title'  type='text' ><br>\n");
		out.println("여행지: <input name='place'  type='text' ><br>\n");
		out.println("여행인원: <input name='person'  type='number' >명<br>\n");
		out.println("시작일: <input name='sdt'  type='date' ><br>\n");
		out.println("종료일: <input name='edt'  type='date' ><br>\n");
		out.println("예상 경비: <input name='money'  type='number'>만원<br>\n");
		out.println("<button>등록</button>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");	
	  
  }

}
