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
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.service.BoardService;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardService.get(no);

      request.getRequestDispatcher("/header").include(request, response);
      out.println("<h1>게시물 변경</h1>");

      if (board == null) {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");
      } else {
        out.println("<form action='update' method='post'>");
        out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", //
            board.getNo());
        out.println("내용:<br>");
        out.printf("<textarea name='text' rows='5' cols='60'>%s</textarea><br>\n", //
            board.getText());
        out.printf("등록일: %s<br>\n", //
            board.getDate());
        out.printf("조회수: %d<br>\n", //
            board.getViewCount());
        out.println("<button>변경</button>");
        out.println("</form>");
      }

      request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "board/list");
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
      BoardService boardService = iocContainer.getBean(BoardService.class);

      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setText(request.getParameter("text"));

      if (boardService.update(board) > 0) {
        response.sendRedirect("list");
      } else {
        throw new Exception("변경 오류");
      }


    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.setAttribute("url", "board/list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

}
