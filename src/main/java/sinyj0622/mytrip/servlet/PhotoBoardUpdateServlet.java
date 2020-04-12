package sinyj0622.mytrip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.service.PhotoBoardService;
@MultipartConfig(maxFileSize = 5000000)
@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));
    int planNo = 0;
    try {

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      PhotoBoard photoBoard = photoBoardService.get(no);
      photoBoard.setTitle(request.getParameter("title"));

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      Collection<Part> parts = request.getParts();
      String dirPath = request.getServletContext().getRealPath("/upload/photoboard");
      for (Part photoPart : parts) {
    	  if(!photoPart.getName().equals("file") || photoPart.getSize() <= 0) {
    		  continue;
    	  } else {
    		  String filename = UUID.randomUUID().toString();
    		  photoPart.write(dirPath + "/" + filename);
    		  photoFiles.add(new PhotoFile().setFilepath(filename));
    	  }
      }

      if (photoFiles.size() > 0) {
        photoBoard.setFiles(photoFiles);
      } else {
        photoBoard.setFiles(null);
      }

      planNo = Integer.parseInt(request.getParameter("planNo"));
      photoBoardService.update(photoBoard);
      response.sendRedirect("list?planNo=" + planNo);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?planNo=" + planNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}