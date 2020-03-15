package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;

public class MemberListServlet implements Servlet {

	MemberService memberService;

	public MemberListServlet(MemberService memberService) {
		this.memberService = memberService;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {

		List<Member> members = memberService.list();
		for (Member m : members) {
			out.printf("%d, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
					m.getPhonenumber(), m.getRegisteredDate());
			
			out.flush();
		}
	}
}
