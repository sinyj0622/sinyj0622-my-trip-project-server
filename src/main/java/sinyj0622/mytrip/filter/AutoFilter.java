package sinyj0622.mytrip.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sinyj0622.mytrip.domain.Member;

// @WebFilter("/*")
public class AutoFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String servletPath = httpRequest.getServletPath();

    if (servletPath.endsWith("add") || servletPath.endsWith("update")
        || servletPath.endsWith("delete")) {
      Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        httpResponse.sendRedirect("../auth/login");
        return;
      }
    }

    chain.doFilter(request, response);
  }
}
