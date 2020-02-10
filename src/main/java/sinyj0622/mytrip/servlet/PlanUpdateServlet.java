package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.PlanObjectFileDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanUpdateServlet implements Servlet {

	PlanObjectFileDao planDao;
	
	public PlanUpdateServlet(PlanObjectFileDao planDao) {
		this.planDao = planDao;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Plan plan = (Plan) in.readObject();
			
			
			if (planDao.update(plan) > 0) { // 업데이트할 게시물을 찾았다면,
				out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
				out.flush();
			}
	}
}
