package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/plan/addForm")
public class PlanAddFormServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	try {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	
	  	out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>여행 플랜 작성</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>여행 플랜 작성</h1>");
		out.println("<form action='add'>"); 
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
	} catch (Exception e) {
		throw new ServletException(e);
	}
	  
  }

}
