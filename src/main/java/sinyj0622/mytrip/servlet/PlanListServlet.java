package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sinyj0622.mytrip.dao.json.PlanJsonFileDao;

public class PlanListServlet implements Servlet {

  PlanJsonFileDao planDao;

  public PlanListServlet(PlanJsonFileDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(planDao.findAll());

  }

}
