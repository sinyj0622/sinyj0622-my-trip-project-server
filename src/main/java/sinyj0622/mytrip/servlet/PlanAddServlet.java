package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.dao.PlanObjectDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanAddServlet implements Servlet {

	PlanObjectDao planDao;
	
	public PlanAddServlet(PlanObjectDao planDao) {
		this.planDao = planDao;
	}
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Plan plan = (Plan) in.readObject();

			
			if (planDao.insert(plan) > 0) {
				out.writeUTF("OK");
				

			} else {
				out.writeUTF("FAIL");
				out.writeUTF("같은 번호의 게시물이 있습니다.");

			}
	}

}
