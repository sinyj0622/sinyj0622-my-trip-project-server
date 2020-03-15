package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;
import sinyj0622.util.Prompt;

public class MemberAddServlet implements Servlet {

	MemberService memberService;

	public MemberAddServlet(MemberService memberService) {
		this.memberService = memberService;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		Member member = new Member();
		member.setName(Prompt.getString(in, out, "이름? "));
		member.setNickname(Prompt.getString(in, out, "별명? "));
		member.setPassWord(Prompt.getString(in, out, "암호? "));
		member.setEmail(Prompt.getString(in, out, "이메일? "));
		member.setMyphoto(Prompt.getString(in, out, "사진? "));
		member.setPhonenumber(Prompt.getString(in, out, "전화? "));
		member.setRegisteredDate(new Date(System.currentTimeMillis()));


		if (memberService.add(member) > 0) {
			out.println("회원을 등록하였습니다.");
		} else {
			out.println("같은 번호의 회원이 있습니다.");

		}

	}

}
