package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.DataLoaderListener;
import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

	PhotoBoardDao photoBoardDao;
	PhotoFileDao photoFileDao;

	public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao,PhotoFileDao photoFileDao) {
		this.photoBoardDao = photoBoardDao;
		this.photoFileDao = photoFileDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "사진 게시글번호? ");

		//DataLoaderListener.con.setAutoCommit(false);
		try {
			if (photoFileDao.deleteAll(no) == 0) {
				throw new Exception("게시글을 찾을 수 없습니다.");
			}
				if (photoBoardDao.delete(no) > 0) {
				out.println("삭제하였습니다.");
				//DataLoaderListener.con.commit();
			}

			
		} catch (Exception e) {
			out.println(e.getMessage());
			//DataLoaderListener.con.rollback();
			
		} finally {
			//DataLoaderListener.con.setAutoCommit(true);
		}
	}

}
