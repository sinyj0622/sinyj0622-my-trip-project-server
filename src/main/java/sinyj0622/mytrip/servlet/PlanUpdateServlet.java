package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanUpdateServlet implements Servlet {

	PlanDao planDao;

	public PlanUpdateServlet(PlanDao planDao) {
		this.planDao = planDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "번호? ");

		Plan oldPlan = planDao.findByNo(no);
		Plan newPlan = new Plan();
		
		if (oldPlan == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newPlan.setNo(oldPlan.getNo());
		newPlan.setTravelTitle(Prompt.getString(in, out,
				String.format("여행제목(%s)? ",oldPlan.getTravelTitle()), oldPlan.getTravelTitle()));
		newPlan.setDestnation(Prompt.getString(in, out,
				String.format("어디로 떠나세요(%s)? ",oldPlan.getDestnation()), oldPlan.getDestnation()));
		newPlan.setPerson(Prompt.getString(in, out,
				String.format("여행인원(%s)? ",oldPlan.getPerson()), oldPlan.getPerson()));
		newPlan.setStartDate(Prompt.getString(in, out,
				String.format("여행 시작일(%s)? ",oldPlan.getStartDate()), oldPlan.getStartDate()));
		newPlan.setEndDate(Prompt.getString(in, out,
				String.format("여행 종료일 (%s)? ",oldPlan.getEndDate()), oldPlan.getEndDate()));
		newPlan.setTravelMoney(Prompt.getString(in, out,
				String.format("예상 경비(%s)? ",oldPlan.getTravelMoney()), oldPlan.getTravelMoney()));

		if (planDao.update(newPlan) > 0) { 
			out.println("여행일정 수정완료");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}
}
