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
import sinyj0622.util.Prompt;

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
		int planNo = Prompt.getInt(in, out, "플랜 번호? ");

		Plan plan = planDao.findByNo(planNo);
		if (plan == null) {
			out.println("해당 번호의 플랜을 찾을 수 없습니다");
		}

		PhotoBoard photoBoard = new PhotoBoard();
		photoBoard.setTitle(Prompt.getString(in, out, "내용? "));
		photoBoard.setPlan(plan);

		if (photoBoardDao.insert(photoBoard) > 0) {

			out.println("최소 한 개의 사진파일을 등록해야 합니다.");
			out.println("파일명 입력없이 그냥 엔터를 치면 파일 추가를 마칩니다..");

			ArrayList<PhotoFile> photoFiles = new ArrayList<>();
			while (true) {
				String filepath = Prompt.getString(in, out, "사진파일? ");

				if (filepath.length() == 0) {
					if (photoFiles.size() > 0) {
						break;
					} else {
						out.println("최소 한 개의 사진파일을 등록해야 합니다.");
						continue;
					}
				}

				photoFiles.add(new PhotoFile() //
						.setFilepath(filepath)//
						.setBoardNo(photoBoard.getNo())); //
			}

			for (PhotoFile photoFile : photoFiles) {
				photoFileDao.insert(photoFile);
			}

			out.println("사진 게시글을 등록했습니다!");

		} else {
			out.println("사진 게시글 등록에 실패하였습니다.");
		}
	}

}
