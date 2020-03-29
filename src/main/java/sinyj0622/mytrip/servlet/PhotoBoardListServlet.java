package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PhotoBoardListServlet {

  PlanService planService;
  PhotoBoardService photoBoardService;

  public PhotoBoardListServlet(PlanService planService, PhotoBoardService photoBoardService) {
    this.planService = planService;
    this.photoBoardService = photoBoardService;
  }



  @RequestMapping("/photoboard/list")
  public void service(Map<String,String> params, PrintStream out) throws Exception {
	  int planNo = Integer.parseInt(params.get("planNo"));

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("  <meta charset='UTF-8'>");
		out.println("  <title>여행 사진 목록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("  <h1>여행 사진 목록</h1>");
		out.printf("  <a href='/photoboard/addForm?planNo=%d'>새 사진</a><br>", planNo);
		out.println("  <table border='1'>");
		out.println("  <tr>");
		out.println("    <th>번호</th>");
		out.println("    <th>제목</th>");
		out.println("    <th>등록일</th>");
		out.println("    <th>조회수</th>");
		out.println("  </tr>");

		List<PhotoBoard> photoBoards = photoBoardService.listPlanPhoto(planNo);
		for (PhotoBoard photoBoard : photoBoards) {
			out.printf("  <tr>"//
					+ "<td>%d</td> "//
					+ "<td><a href='/photoboard/detail?no=%d&planNo=%d'>%s</a></td> "//
					+ "<td>%s</td> "//
					+ "<td>%d</td> "//
					+ "</tr>\n", //
					photoBoard.getNo(),
					photoBoard.getNo(),
					planNo,//
					photoBoard.getTitle(),
					photoBoard.getCreatedDate(),
					photoBoard.getViewCount()
					);
		}  out.println("</table>");

		out.println("</body>");
		out.println("</html>");
	}

}
