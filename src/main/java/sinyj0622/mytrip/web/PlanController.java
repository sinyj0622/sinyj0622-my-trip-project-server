package sinyj0622.mytrip.web;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {

  static Logger logger = LogManager.getLogger(PlanController.class);

  public PlanController() {
    logger.debug("PlanController 생성됨!");
  }

  @Autowired
  PlanService planService;

  @GetMapping("form")
  public void form() throws Exception {}

  @PostMapping("add")
  public String add(Plan plan) throws Exception {
    planService.add(plan);
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (planService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("삭제할 게시물 번호가 유효하지 않습니다.");
    }
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    Plan plan = planService.get(no);
    model.addAttribute("plan", plan);
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    List<Plan> plans = planService.list();
    model.addAttribute("list", plans);
  }

  @GetMapping("updateForm")
  public void updateForm(int no, Model model) throws Exception {
    model.addAttribute("plan", planService.get(no));
  }

  @PostMapping("update")
  public String update(Plan plan) throws Exception {
    if (planService.update(plan) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("변경할 게시물 번호가 유효하지 않습니다.");
    }
  }
}
