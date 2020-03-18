package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class PhotoBoardDeleteServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }



  @RequestMapping("/photoboard/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "사진 게시글번호? ");

    photoBoardService.delete(no);
    out.println("삭제하였습니다.");
  }

}
