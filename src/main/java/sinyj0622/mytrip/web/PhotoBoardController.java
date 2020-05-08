package sinyj0622.mytrip.web;


import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import sinyj0622.mytrip.domain.PhotoBoard;
import sinyj0622.mytrip.domain.PhotoFile;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;

@Controller
@RequestMapping("/photoboard")
public class PhotoBoardController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  PhotoBoardService photoBoardService;

  @Autowired
  PlanService planService;

  @GetMapping("form")
  public void form(int planNo, Model model) throws Exception {
    model.addAttribute("plan", planService.get(planNo));
  }

  @PostMapping("add")
  public String add(//
      int planNo, //
      String title, //
      MultipartFile[] photoFiles) throws Exception {
    Plan plan = planService.get(planNo);
    if (plan == null) {
      throw new Exception("수업 번호가 유효하지 않습니다.");
    }

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(title);
    photoBoard.setPlan(plan);

    ArrayList<PhotoFile> photos = new ArrayList<>();
    String dirPath = servletContext.getRealPath("/upload/photoboard");
    for (MultipartFile photoFile : photoFiles) {
      if (photoFile.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      photoFile.transferTo(new File(dirPath + "/" + filename));
      photos.add(new PhotoFile().setFilepath(filename));
    }

    if (photos.size() == 0) {
      throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
    }

    photoBoard.setFiles(photos);
    photoBoardService.add(photoBoard);

    return "redirect:list?planNo=" + planNo;

  }

  @GetMapping("delete")
  public String delete(int no, int planNo) throws Exception {
    photoBoardService.delete(no);
    return "redirect:list?planNo=" + planNo;
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    model.addAttribute("photoBoard", photoBoardService.get(no));
  }

  @GetMapping("list")
  public void list(int planNo, Model model) throws Exception {
    Plan plan = planService.get(planNo);
    if (plan == null) {
      throw new Exception("수업 번호가 유효하지 않습니다.");
    }
    model.addAttribute("plan", plan);
    model.addAttribute("list", photoBoardService.listPlanPhoto(planNo));
  }

  @PostMapping("update")
  public String update(//
      int no, //
      String title, //
      MultipartFile[] photoFiles) throws Exception {

    PhotoBoard photoBoard = photoBoardService.get(no);
    photoBoard.setTitle(title);

    ArrayList<PhotoFile> photos = new ArrayList<>();
    String dirPath = servletContext.getRealPath("/upload/photoboard");
    for (MultipartFile photoFile : photoFiles) {
      if (photoFile.getSize() <= 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      photoFile.transferTo(new File(dirPath + "/" + filename));
      photos.add(new PhotoFile().setFilepath(filename));
    }

    if (photos.size() > 0) {
      photoBoard.setFiles(photos);
    } else {
      photoBoard.setFiles(null);
    }

    int planNo = photoBoard.getPlan().getNo();
    photoBoardService.update(photoBoard);
    return "redirect:list?planNo=" + planNo;
  }
}
