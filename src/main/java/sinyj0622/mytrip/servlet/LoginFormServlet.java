package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.util.RequestMapping;

@Component
public class LoginFormServlet {


  @RequestMapping("/auth/loginForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");
    out.println("<form action='/auth/login'>");
    out.println("이메일: <input name='email' type='email'><br>");
    out.println("암호: <input name='password' type='passWord'><br>");
    out.println("<button>로그인</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
