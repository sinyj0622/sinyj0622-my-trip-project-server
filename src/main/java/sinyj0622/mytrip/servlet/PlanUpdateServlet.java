package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanUpdateServlet implements Servlet {

	PlanDao planDao;

	public PlanUpdateServlet(PlanDao planDao) {
		this.planDao = planDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		out.println("번호? ");
		out.println("!@#");
		out.flush();
		int no = Integer.parseInt(in.nextLine());

		Plan oldPlan = planDao.findByNo(no);
		Plan newPlan = new Plan();
		
		if (oldPlan == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newPlan.setNo(oldPlan.getNo());
	
		out.printf("여행제목(%s)? \n", oldPlan.getTravelTitle());
		out.println("!@#");		
		newPlan.setTravelTitle(in.nextLine());
		
		out.printf("어디로 떠나세요(%s)? \n", oldPlan.getDestnation(), oldPlan.getDestnation());
		out.println("!@#");		
		newPlan.setDestnation(in.nextLine());
		
		out.printf("여행인원(%s)? \n", oldPlan.getPerson());
		out.println("!@#");		
		newPlan.setPerson(in.nextLine());
		
		out.printf("여행 시작일 (%s)? \n", oldPlan.getStartDate());
		out.println("!@#");		
		newPlan.setStartDate(in.nextLine());
		
		out.printf("여행 종료일 (%s)? \n", oldPlan.getEndDate());
		out.println("!@#");		
		newPlan.setEndDate(in.nextLine());

		out.printf("예상 경비(%s)? \n", oldPlan.getPerson());
		out.println("!@#");		
		newPlan.setTravelMoney(in.nextLine());
		
		out.flush();

		if (planDao.update(newPlan) > 0) { // 업데이트할 게시물을 찾았다면,
			out.println("여행일정 수정완료!");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}
}
