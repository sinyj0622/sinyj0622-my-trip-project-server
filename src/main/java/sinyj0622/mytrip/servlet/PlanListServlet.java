package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.dao.PlanObjectDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanListServlet implements Servlet {

	PlanObjectDao planDao;
	
	public PlanListServlet(PlanObjectDao planDao) {
		this.planDao = planDao;
	}
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
		out.writeUTF("OK");
		out.reset();
		out.writeObject(planDao.findAll());

	}

}
