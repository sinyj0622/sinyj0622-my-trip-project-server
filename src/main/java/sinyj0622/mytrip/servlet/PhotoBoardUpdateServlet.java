package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;

public class PhotoBoardUpdateServlet implements Servlet {

	PhotoBoardDao photoBoardDao;
	PhotoFileDao photoFileDao;

	public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
		this.photoBoardDao = photoBoardDao;
		this.photoFileDao = photoFileDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		out.println("사진 게시글번호? ");
		out.println("!@#");
		out.flush();
		int no = Integer.parseInt(in.nextLine());

		PhotoBoard old = photoBoardDao.findByNo(no);
		PhotoBoard newPhotoBoard = new PhotoBoard();

		if (old == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newPhotoBoard.setNo(old.getNo());

		out.printf("제목(%s)? \n", old.getTitle());
		out.println("!@#");		
		newPhotoBoard.setTitle(in.nextLine());

		out.flush();

		if (photoBoardDao.update(newPhotoBoard) > 0) { // 업데이트할 게시물을 찾았다면,
			out.println("사진 파일:");
			List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(no);
			for (PhotoFile photoFile : oldPhotoFiles) {
				out.printf("> %s\n", photoFile.getFilepath());
			}

			out.println("사진은 일부만 변경할 수 없습니다.");
			out.println("전체를 새로 등록해야 합니다.");
			out.println("사진을 변경하시겠습니까?(y/N)");
			out.println("!@#");
			out.flush();
			String response = in.nextLine();

			if (response.equalsIgnoreCase("y")) {
				photoFileDao.deleteAll(no);

				// 새로 등록할 첨부 파일을 입력 받는다.
				out.println("최소 한 개의 사진파일을 등록해야 합니다.");
				out.println("파일명 입력없이 그냥 엔터를 치면 파일 추가를 마칩니다..");

				ArrayList<PhotoFile> photoFiles = new ArrayList<>();
				while (true) {
					out.println("사진파일? ");
					out.println("!@#");
					out.flush();
					String filepath = in.nextLine();

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
							.setBoardNo(newPhotoBoard.getNo())); //
				}

				for (PhotoFile photoFile : photoFiles) {
					photoFileDao.insert(photoFile);
				}

			} 
			

		      out.println("사진 게시글을 변경했습니다!");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}
}
