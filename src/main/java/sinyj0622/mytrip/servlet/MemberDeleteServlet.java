package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.util.Prompt;

public class MemberDeleteServlet implements Servlet {

  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
	  int no = Prompt.getInt(in, out, "번호? ");

    if (memberDao.delete(no) > 0) {
      out.println("해당 회원 데이터를 삭제하였습니다.");
    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }

}
