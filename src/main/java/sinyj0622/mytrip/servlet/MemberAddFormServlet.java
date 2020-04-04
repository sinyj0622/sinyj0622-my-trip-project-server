package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.util.RequestMapping;

@Component
public class MemberAddFormServlet {

  @RequestMapping("/member/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 입력</h1>");
    out.println("<form action='/member/add'>");
    out.println("이름: <input name='name' type='text'><br>");
    out.println("별명: <input name='nickname' type='text'><br>");
    out.println("이메일: <input name='email' type='text'><br>");
    out.println("암호: <input name='passWord' type='text'><br>");
    out.println("사진: <input name='myphoto' type='text'><br>");
    out.println("전화: <input name='phonenumber' type='text'><br>");
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
