package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sinyj0622.mytrip.dao.json.PlanJsonFileDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanDetailServlet implements Servlet {

  PlanJsonFileDao planDao;

  public PlanDetailServlet(PlanJsonFileDao planDao) {
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
