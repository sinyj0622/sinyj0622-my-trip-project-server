package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberListServlet implements Servlet {

	MemberDao memberDao;

	public MemberListServlet(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {

		List<Member> members = memberDao.findAll();
		for (Member m : members) {
			out.printf("%d, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
					m.getPhonenumber(), m.getRegisteredDate());
			
			out.flush();
		}
	}
}
