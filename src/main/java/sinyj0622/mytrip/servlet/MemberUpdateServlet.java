package sinyj0622.mytrip.servlet;

import java.io.IOException;
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

@MultipartConfig(maxFileSize = 5000000)
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

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
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassWord(request.getParameter("password"));
      member.setPhonenumber(request.getParameter("tel"));

      Part photoPart = request.getPart("myphoto");
      if (photoPart.getSize() > 0) {
        String dirPath = request.getServletContext().getRealPath("/upload/member");
        String filename = UUID.randomUUID().toString();
        photoPart.write(dirPath + "/" + filename);
        member.setMyphoto(filename);
      }

      if (memberService.update(member) > 0) {
        response.sendRedirect("list");
      } else {
        throw new Exception("변경 오류");
      }


    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      System.out.println(e.getMessage());
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
