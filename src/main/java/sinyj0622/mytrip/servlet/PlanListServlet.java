package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.domain.Plan;

public class PlanListServlet implements Servlet {

	List<Plan> plans;
	
	public PlanListServlet(List<Plan> plans) {
		this.plans = plans;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
		out.writeUTF("OK");
		out.reset();
		out.writeObject(plans);

	}

}
