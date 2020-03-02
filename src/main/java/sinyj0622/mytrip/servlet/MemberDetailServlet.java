package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberDetailServlet implements Servlet {

	MemberDao memberDao;

	public MemberDetailServlet(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "번호? ");

		Member member = memberDao.findByNo(no);

		if (member != null) {
			out.printf("번호: %d\n", member.getNo());
			out.printf("이름: %s\n", member.getName());
			out.printf("별명: %s\n", member.getNickname());
			out.printf("이메일: %s\n", member.getEmail());
			out.printf("사진: %s\n", member.getMyphoto());
			out.printf("전화: %s\n", member.getPhonenumber());
			out.printf("등록일: %s\n", member.getRegisteredDate());
			out.flush();
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
		}
	}

}
