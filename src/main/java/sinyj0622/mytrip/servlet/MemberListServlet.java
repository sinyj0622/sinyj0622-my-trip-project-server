package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sinyj0622.mytrip.dao.MemberDao;

public class MemberListServlet implements Servlet {

  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberDao.findAll());

  }

}
