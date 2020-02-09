package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.domain.Plan;

public class PlanUpdateServlet implements Servlet {

	List<Plan> plans;
	
	public PlanUpdateServlet(List<Plan> plans) {
		this.plans = plans;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			Plan plan = (Plan) in.readObject();

			int index = -1;
			for (int i = 0; i < plans.size(); i++) {
				if (plans.get(i).getNo() == plan.getNo()) {
					index = i;
					break;
				}
			}

			if (index != -1) { // 업데이트할 게시물을 찾았다면,
				plans.set(index, plan);
				out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
				out.flush();
			}
	}
}
