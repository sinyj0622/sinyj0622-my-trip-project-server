package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sinyj0622.mytrip.dao.json.MemberJsonFileDao;

public class MemberDeleteServlet implements Servlet {

  MemberJsonFileDao memberDao;

  public MemberDeleteServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }

}
