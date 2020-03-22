package sinyj0622.mytrip.service.Impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.service.PhotoBoardService;

@Component
public class PhotoBoardServiceImpl implements PhotoBoardService {

	PhotoBoardDao photoBoardDao;
	PhotoFileDao photoFileDao;
	TransactionTemplate transactionTemplate;

	public PhotoBoardServiceImpl(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
			PlatformTransactionManager txManager) {
		this.photoBoardDao = photoBoardDao;
		this.photoFileDao = photoFileDao;
		this.transactionTemplate = new TransactionTemplate(txManager);
	}

	@Transactional
	@Override
	public void add(PhotoBoard photoBoard) throws Exception {
		if (photoBoardDao.insert(photoBoard) == 0) {
			throw new Exception("사진 게시글 등록에 실패하였습니다.");
		}
		photoFileDao.insert(photoBoard);
	}

	@Transactional
	@Override
	public void delete(int no) throws Exception {
		photoFileDao.deleteAll(no);
		if (photoBoardDao.delete(no) == 0) {
			throw new Exception("게시글을 찾을 수 없습니다.");
		}
	}

	@Override
	public PhotoBoard get(int no) throws Exception {
		return photoBoardDao.findByNo(no);
	}

	@Override
	public List<PhotoBoard> listPlanPhoto(int no) throws Exception {
		return photoBoardDao.findAllByPlanNo(no);
	}
	
	
	@Transactional
	@Override
	public void update(PhotoBoard photoBoard) throws Exception {
		if (photoBoardDao.update(photoBoard) == 0) {
			throw new Exception("사진 게시글 변경에 실패했습니다.");
		}

		if (photoBoard.getFiles() != null) {
			photoFileDao.deleteAll(photoBoard.getNo());
			photoFileDao.insert(photoBoard);
		}
	}
}
