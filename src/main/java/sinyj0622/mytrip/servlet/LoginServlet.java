package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      request.getRequestDispatcher("/header").include(request, response);
      out.println("<h1>로그인</h1>");
      out.println("<form action='login' method='post'>");
      out.println("이메일: <input name='email' type='email'><br>");
      out.println("암호: <input name='password' type='passWord'><br>");
      out.println("<button>로그인</button>");
      out.println("</form>");
      request.getRequestDispatcher("/footer").include(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      String email = request.getParameter("email");
      String password = request.getParameter("passWord");

      Member member = memberService.get(email, password);

      if (member != null) {
        response.sendRedirect("/sinyj0622-my-trip-project-server");
      } else {
        throw new Exception("로그인 실패");
      }

    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "/sinyj0622-my-trip-project-server");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
