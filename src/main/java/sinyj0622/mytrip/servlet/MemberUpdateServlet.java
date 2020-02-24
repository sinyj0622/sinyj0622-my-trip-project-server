package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberUpdateServlet implements Servlet {

	MemberDao memberDao;

	public MemberUpdateServlet(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {

		out.println("번호? ");
		out.println("!@#");
		int no = Integer.parseInt(in.nextLine());

		Member oldMember = memberDao.findByNo(no);
		Member newMember = new Member();
		
		if (oldMember == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newMember.setNo(oldMember.getNo());

		out.printf("이름(%s)? \n", oldMember.getName());
		out.println("!@#");		
		newMember.setName(in.nextLine());

		out.printf("별명(%s)? \n", oldMember.getNickname());
		out.println("!@#");		
		newMember.setNickname(in.nextLine());
		
		out.printf("이메일(%s)? \n", oldMember.getEmail());
		out.println("!@#");		
		newMember.setEmail(in.nextLine());
		
		out.printf("사진(%s)? \n", oldMember.getMyphoto());
		out.println("!@#");		
		newMember.setMyphoto(in.nextLine());
		
		out.printf("전화(%s)? \n", oldMember.getPhonenumber());
		out.println("!@#");		
		newMember.setPhonenumber(in.nextLine());

		out.flush();

		if (memberDao.update(newMember) > 0) {
			out.println("회원 정보를 수정하였습니다.");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
			out.flush();
		}
	}

}
