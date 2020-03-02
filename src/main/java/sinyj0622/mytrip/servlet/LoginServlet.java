package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class LoginServlet implements Servlet {

	MemberDao memberDao;

	public LoginServlet(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		String password = Prompt.getString(in, out, "암호: ");
		String email = Prompt.getString(in, out, "이메일: ");

		Member member = memberDao.findByEmailAndPassword(email, password);

		if (member != null) {
			out.printf("'%s' 님 로그인 완료\n",  member.getName());
			out.flush();
		} else {
			out.println("로그인 실패");
		}
	}

}
