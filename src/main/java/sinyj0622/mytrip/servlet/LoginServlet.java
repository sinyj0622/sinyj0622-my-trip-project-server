package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;
import sinyj0622.util.Prompt;

public class LoginServlet implements Servlet {

	MemberService memberService;

	public LoginServlet(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		String email = Prompt.getString(in, out, "이메일: ");
		String password = Prompt.getString(in, out, "암호: ");

		Member member = memberService.findByEmailAndPassword(email, password);

		if (member != null) {
			out.printf("'%s' 님 로그인 완료\n",  member.getName());
			out.flush();
		} else {
			out.println("로그인 실패");
		}
	}

}
