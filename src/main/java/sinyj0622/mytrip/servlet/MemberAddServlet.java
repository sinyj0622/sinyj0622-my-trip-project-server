package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;
@MultipartConfig(maxFileSize = 10000000)
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      request.getRequestDispatcher("/header").include(request, response);
      out.println("<h1>회원 입력</h1>");
      out.println("<form action='add' method='post' enctype='multipart/form-data'>");
      out.println("이름: <input name='name' type='text'><br>");
      out.println("별명: <input name='nickname' type='text'><br>");
      out.println("이메일: <input name='email' type='text'><br>");
      out.println("암호: <input name='passWord' type='password'><br>");
      out.println("사진: <input name='myphoto' type='file'><br>");
      out.println("전화: <input name='phonenumber' type='text'><br>");
      out.println("<button>제출</button>");
      out.println("</form>");

      request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
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
      
      Part photoPart = request.getPart("myphoto");
      if (photoPart.getSize() > 0) {
    	  String dirPath = request.getServletContext().getRealPath("/upload/member");
    	  String filename = UUID.randomUUID().toString();
    	  photoPart.write(dirPath + "/" + filename);
    	  
    	  member.setMyphoto(filename);
      }
      member.setPhonenumber(request.getParameter("phonenumber"));

      if (memberService.add(member) > 0) {
        response.sendRedirect("list");
      } else {
        throw new Exception("등록 오류");
      }

    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
