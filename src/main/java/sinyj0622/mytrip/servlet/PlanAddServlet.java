package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.domain.Plan;

public class PlanAddServlet implements Servlet {

	List<Plan> plans;
	
	public PlanAddServlet(List<Plan> plans) {
		this.plans = plans;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Plan plan = (Plan) in.readObject();

			int i;
			for (i = 0; i < plans.size(); i++) {
				if (plans.get(i).getNo() == plan.getNo()) {
					break;
				}
			}

			if (i == plans.size()) {
				plans.add(plan);
				out.writeUTF("OK");

			} else {
				out.writeUTF("FAIL");
				out.writeUTF("같은 번호의 게시물이 있습니다.");

			}
	}

}
