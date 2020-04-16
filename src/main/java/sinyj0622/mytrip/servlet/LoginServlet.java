package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
      String email = "";
      Cookie[] cookies = request.getCookies();
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("email")) {
          email = cookie.getValue();
          break;
        }
      }

      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("email", email);
      request.getRequestDispatcher("/auth/form.jsp").include(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String userEmail = request.getParameter("userEmail");

      Cookie cookie = new Cookie("email", email);
      if (userEmail == null) {
        cookie.setMaxAge(0);
      } else {
        cookie.setMaxAge(60 * 60 * 24 * 30);
      }
      response.addCookie(cookie);

      Member member = memberService.get(email, password);

      if (member != null) {
        request.getSession().setAttribute("loginUser", member); // 사용자 로그인 정보 저장
        response.sendRedirect("../index.html");
      } else {
        response.sendRedirect("/auth/login");
      }

    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "/sinyj0622-my-trip-project-server");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
