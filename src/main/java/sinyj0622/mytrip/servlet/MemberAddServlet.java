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

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>회원 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원 입력</h1>");
      out.println("<form action='add' method='post'>");
      out.println("이름: <input name='name' type='text'><br>");
      out.println("별명: <input name='nickname' type='text'><br>");
      out.println("이메일: <input name='email' type='text'><br>");
      out.println("암호: <input name='passWord' type='password'><br>");
      out.println("사진: <input name='myphoto' type='text'><br>");
      out.println("전화: <input name='phonenumber' type='text'><br>");
      out.println("<button>제출</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
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

      Member member = new Member();
      member.setName(request.getParameter("name"));
      member.setNickname(request.getParameter("nickname"));
      member.setEmail(request.getParameter("email"));
      member.setPassWord(request.getParameter("passWord"));
      member.setMyphoto(request.getParameter("myphoto"));
      member.setPhonenumber(request.getParameter("phonenumber"));

      if (memberService.add(member) > 0) {
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessaget", "회원을 추가할 수 없습니다.");
        request.getSession().setAttribute("url", "member/list");
        response.sendRedirect("../error");
      }


    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
