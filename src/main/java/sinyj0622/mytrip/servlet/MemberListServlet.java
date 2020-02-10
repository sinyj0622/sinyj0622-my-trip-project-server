package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sinyj0622.mytrip.dao.json.MemberJsonFileDao;

public class MemberListServlet implements Servlet {

  MemberJsonFileDao memberDao;

  public MemberListServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(memberDao.findAll());

  }

}
