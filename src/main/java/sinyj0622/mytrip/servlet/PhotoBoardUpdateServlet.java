package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class PhotoBoardUpdateServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }



  @RequestMapping("/photoboard/update")
  public void service(Map<String,String> params, PrintStream out) throws Exception {
	  int no = Integer.parseInt(params.get("no"));
	    PhotoBoard photoBoard = photoBoardService.get(no);
	    photoBoard.setTitle(params.get("title"));

	    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
	    for (int i = 1; i < 6; i++) {
	      String filepath = params.get("file" + i);
	      if (filepath.length() > 0) {
	        photoFiles.add(new PhotoFile().setFilepath(filepath));
	      }
	    }

	    if (photoFiles.size() > 0) {
	      photoBoard.setFiles(photoFiles);
	    } else {
	      photoBoard.setFiles(null);
	    }

	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta charset='UTF-8'>");
	    out.println("<title>사진 변경</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>사진 변경 결과</h1>");

	    try {
	      photoBoardService.update(photoBoard);
	      out.println("<p>사진을 변경했습니다.</p>");
	    } catch (Exception e) {
	      out.println("<p>해당 사진 게시물이 존재하지 않습니다.</p>");
	    }
		out.printf("  <a href='/photoboard/list?planNo=%d'>목록</a>",photoBoard.getPlan().getNo());
	    out.println("</body>");
	    out.println("</html>");
	  }
}
