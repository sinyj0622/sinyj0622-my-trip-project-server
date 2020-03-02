package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanDetailServlet implements Servlet {

	PlanDao planDao;

	public PlanDetailServlet(PlanDao planDao) {
		this.planDao = planDao;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "번호? ");

		Plan p = planDao.findByNo(no);
		if (p != null) {
			out.printf("번호: %d\n", p.getNo());
			out.printf("여행 제목: %s\n", p.getTravelTitle());
			out.printf("어디로 떠나세요: %s\n", p.getDestnation());
			out.printf("여행인원: %s\n", p.getPerson());
			out.printf("여행 기간: %s ~ %s\n", p.getStartDate(), p.getEndDate());
			out.printf("예상 경비: %s\n", p.getTravelMoney());
			
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}

}
