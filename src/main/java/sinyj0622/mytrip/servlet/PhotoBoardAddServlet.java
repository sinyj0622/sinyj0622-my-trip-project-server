package sinyj0622.mytrip.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.util.RequestMapping;

@Component
public class PhotoBoardAddServlet {

	PlanService planService;
	PhotoBoardService photoBoardService;

	public PhotoBoardAddServlet(PlanService planService, PhotoBoardService photoBoardService) {
		this.planService = planService;
		this.photoBoardService = photoBoardService;
	}



	@RequestMapping("/photoboard/add")
	public void service(Map<String,String> params, PrintWriter out) throws Exception {
		int planNo = Integer.parseInt(params.get("planNo"));
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta charset='UTF-8'>");
	    out.println("<meta http-equiv='refresh'" //
	        + " content='2;url=/photoboard/list?planNo=" + planNo + "'>");
	    out.println("<title>사진 입력</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>사진 입력 결과</h1>");

		try {
			Plan plan = planService.get(planNo);
			PhotoBoard photoBoard = new PhotoBoard();
			photoBoard.setTitle(params.get("title"));
			photoBoard.setPlan(plan);

			ArrayList<PhotoFile> photoFiles = new ArrayList<>();
			for (int i = 1; i < 6; i++) {
				String filepath = params.get("file" + i);
				if (filepath.length() > 0) {
					photoFiles.add(new PhotoFile().setFilepath(filepath));
				}
			}


			if (photoFiles.size() == 0) {
				throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
			}
			photoBoard.setFiles(photoFiles);

			photoBoardService.add(photoBoard);
			out.println("<p>사진 게시글을 등록했습니다!</p>");
		}catch (Exception e) {
			out.printf("<p>%s</p>\n", e.getMessage());

		}
		out.println("</body>");
		out.println("</html>");

	}

}

