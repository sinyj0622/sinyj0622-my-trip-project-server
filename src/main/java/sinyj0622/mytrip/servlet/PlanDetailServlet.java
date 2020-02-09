package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.PlanObjectDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanDetailServlet implements Servlet {

	PlanObjectDao planDao;
	
	public PlanDetailServlet(PlanObjectDao planDao) {
		this.planDao = planDao;
	}
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			int no = in.readInt();

			Plan plan = planDao.findByNo(no);
			if (plan != null) {
				out.writeUTF("OK");
				out.writeObject(plan);

			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
			}
	}

}
