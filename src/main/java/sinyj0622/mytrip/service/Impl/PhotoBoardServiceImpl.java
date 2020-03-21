package sinyj0622.mytrip.service.Impl;

import java.util.List;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.dao.PhotoBoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.TransactionTemplate;

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

  @Override
  public void add(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(() -> {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시글 등록에 실패하였습니다.");
      }
      photoFileDao.insert(photoBoard);
      return null;
    });
  }


  @Override
  public void delete(int no) throws Exception {
    transactionTemplate.execute(() -> {
      if (photoFileDao.deleteAll(no) == 0) {
        throw new Exception("게시글을 찾을 수 없습니다.");
      }
      if (photoBoardDao.delete(no) > 0) {
      }
      return null;

    });
  }

  @Override
  public PhotoBoard get(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }

  @Override
  public List<PhotoBoard> listPlanPhoto(int no) throws Exception {
    return photoBoardDao.findAllByPlanNo(no);
  }

  @Override
  public void update(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(() -> {

      if (photoBoardDao.update(photoBoard) == 0) {
        throw new Exception("사진 게시글 변경에 실패했습니다.");
      }

      if (photoBoard.getFiles() != null) {
        photoFileDao.deleteAll(photoBoard.getNo());
        photoFileDao.insert(photoBoard);
      }
      return null;
    });
  }
}
