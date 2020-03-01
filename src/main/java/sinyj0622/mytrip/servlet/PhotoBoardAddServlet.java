package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.domain.Plan;

public class PhotoBoardAddServlet implements Servlet {

	PlanDao planDao;
	PhotoBoardDao photoBoardDao;
	PhotoFileDao photoFileDao;
	
	public PhotoBoardAddServlet(PlanDao planDao, PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
		this.planDao = planDao;
		this.photoBoardDao = photoBoardDao;
		this.photoFileDao = photoFileDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		out.println("플랜 번호? ");
		out.println("!@#");
		int planNo = Integer.parseInt(in.nextLine());

		Plan plan = planDao.findByNo(planNo);
		if (plan == null) {
			out.println("해당 번호의 플랜을 찾을 수 없습니다");
		}

		PhotoBoard photoBoard = new PhotoBoard();
		out.println("내용? ");
		out.println("!@#");
		photoBoard.setTitle(in.nextLine());
		photoBoard.setPlan(plan);

		if (photoBoardDao.insert(photoBoard) > 0 ) {

			out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
			out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

			ArrayList<PhotoFile> photoFiles = new ArrayList<>();

			while(true) {
				out.println("사진 파일?");
				out.println("!@#");
				String filepath = in.nextLine();

				if(filepath.length() == 0) {
					if (photoFiles.size() > 0) {
						break;
					}else {
						out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
						continue;
					}
				} 
				
				PhotoFile photoFile = new PhotoFile();
				photoFile.setBoardNo(photoBoard.getNo());
				photoFile.setFilepath(filepath);
			}
			
			for (PhotoFile photoFile : photoFiles) {
				photoFileDao.insert(photoFile);
				
			}
			out.println("사진게시글 등록 완료");

		} else {
			out.println("게시글 등록 실패");
		}

	}

}
