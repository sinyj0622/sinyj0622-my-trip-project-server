package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.DataLoaderListener;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.util.ConnectionFactory;
import sinyj0622.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {

	PhotoBoardDao photoBoardDao;
	PhotoFileDao photoFileDao;
	PlatformTransactionManager txManager;

	public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, 
			PhotoFileDao photoFileDao,PlatformTransactionManager txManager) {
		this.photoBoardDao = photoBoardDao;
		this.photoFileDao = photoFileDao;
		this.txManager = txManager;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "사진 게시글번호? ");

		PhotoBoard old = photoBoardDao.findByNo(no);
		PhotoBoard newPhotoBoard = new PhotoBoard();

		if (old == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newPhotoBoard.setNo(old.getNo());
		newPhotoBoard.setTitle(Prompt.getString(in, out,
				String.format("제목(%s)? ",old.getTitle()), old.getTitle()));

		txManager.beginTransaction();
		try {
			if (photoBoardDao.update(newPhotoBoard) > 0) { 
				out.println("사진 파일:");
				List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(no);
				for (PhotoFile photoFile : oldPhotoFiles) {
					out.printf("> %s\n", photoFile.getFilepath());
				}

				out.println("사진은 일부만 변경할 수 없습니다.");
				out.println("전체를 새로 등록해야 합니다.");
				String response = Prompt.getString(in, out, "사진을 변경하시겠습니까?(y/N)");

				if (response.equalsIgnoreCase("y")) {
					photoFileDao.deleteAll(no);

					ArrayList<PhotoFile> photoFiles = inputPhotoFiles(in, out, newPhotoBoard);

					for (PhotoFile photoFile : photoFiles) {
						photoFileDao.insert(photoFile);
					}

				} 

				out.println("사진 게시글을 변경했습니다!");
				txManager.commit();
			}
		} catch (Exception e) {
			out.println(e.getMessage());
			txManager.rollback();
			
		} 
	}


	private ArrayList<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out, PhotoBoard newPhotoBoard) {
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
					.setBoardNo(newPhotoBoard.getNo())); //
		}
		return photoFiles;
	}

}
