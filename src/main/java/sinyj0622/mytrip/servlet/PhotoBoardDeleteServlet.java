package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Scanner;

import sinyj0622.mytrip.DataLoaderListener;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.sql.DataSource;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

	PhotoBoardDao photoBoardDao;
	PhotoFileDao photoFileDao;
	PlatformTransactionManager txManager;

	public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao,
			PhotoFileDao photoFileDao,PlatformTransactionManager txManager) {
		this.photoBoardDao = photoBoardDao;
		this.photoFileDao = photoFileDao;
		this.txManager = txManager;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "사진 게시글번호? ");

		txManager.beginTransaction();
		try {
			if (photoFileDao.deleteAll(no) == 0) {
				throw new Exception("게시글을 찾을 수 없습니다.");
			}
				if (photoBoardDao.delete(no) > 0) {
				out.println("삭제하였습니다.");
				txManager.commit();
			}

			
		} catch (Exception e) {
			out.println(e.getMessage());
			txManager.rollback();
			
		} 
	}

}
