package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberSearchServlet implements Servlet {

  MemberDao memberDao;

  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
	String response = Prompt.getString(in, out, "검색어? ");
    List<Member> members = memberDao.findByKeyword(response);

    	for (Member m : members) {
			out.printf("%d, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getNickname(), m.getEmail(),
					m.getMyphoto(), m.getRegisteredDate());
			
			out.flush();
    }
  }

}