package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.domain.Plan;

public class PlanDeleteServlet implements Servlet {

	List<Plan> plans;
	
	public PlanDeleteServlet(List<Plan> plans) {
		this.plans = plans;
	}
	
	
	@Override
	public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
			int no = in.readInt();

			int index = -1;
			for (int i = 0; i < plans.size(); i++) {
				if (plans.get(i).getNo() == no) {
					index = i;
					break;
				}
			}

			if (index != -1) {
				plans.remove(index);
				out.writeUTF("OK");
			} else {
				out.writeUTF("FAIL");
				out.writeUTF("해당 번호의 게시물이 없습니다.");
			}
	}

}
