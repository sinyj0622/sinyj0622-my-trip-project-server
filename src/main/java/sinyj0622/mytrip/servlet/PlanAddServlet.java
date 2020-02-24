package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;

public class PlanAddServlet implements Servlet {

  PlanDao planDao;

  public PlanAddServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
	  Plan plan = new Plan();
	  
	  out.println("여행 제목? ");
	  out.println("!@#");
	  plan.setTravelTitle(in.nextLine());
	  
	  out.println("어디로 떠나세요? ");
	  out.println("!@#");
	  plan.setDestnation(in.nextLine());
	  
	  out.println("여행 인원? ");
	  out.println("!@#");
	  plan.setPerson(in.nextLine());
	  
	  out.println("여행 시작일? ");
	  out.println("!@#");
	  plan.setStartDate(in.nextLine());
	  
	  out.println("여행 종료일? ");
	  out.println("!@#");
	  plan.setEndDate(in.nextLine());
	  
	  out.println("예상 경비? ");
	  out.println("!@#");
	  plan.setTravelMoney(in.nextLine());

    if (planDao.insert(plan) > 0) {
        out.println("신규 여행일정 등록완료!^^");
        
    } else {
      out.println("같은 번호의 게시글이 있습니다.");

    }
  }

}
