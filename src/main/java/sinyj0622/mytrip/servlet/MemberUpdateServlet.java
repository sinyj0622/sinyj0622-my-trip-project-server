package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberUpdateServlet implements Servlet {

	MemberDao memberDao;

	public MemberUpdateServlet(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	@Override
	public void service(Scanner in, PrintStream out) throws Exception {
		int no = Prompt.getInt(in, out, "번호? ");

		Member oldMember = memberDao.findByNo(no);
		Member newMember = new Member();

		if (oldMember == null) {
			out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		newMember.setNo(oldMember.getNo());

		newMember.setName(Prompt.getString(in, out, 
				String.format("이름(%s)? ", oldMember.getName()), oldMember.getName()));
		newMember.setNickname(Prompt.getString(in, out, 
				String.format("별명(%s)? ", oldMember.getNickname()), oldMember.getNickname()));
		newMember.setEmail(Prompt.getString(in, out, 
				String.format("이메일(%s)? ", oldMember.getEmail()), oldMember.getEmail()));
		newMember.setMyphoto(Prompt.getString(in, out, 
				String.format("사진(%s)? ", oldMember.getMyphoto()), oldMember.getMyphoto()));
		newMember.setPhonenumber(Prompt.getString(in, out, 
				String.format("전화(%s)? ", oldMember.getPhonenumber()), oldMember.getPhonenumber()));

		if (memberDao.update(newMember) > 0) {
			out.println("회원 정보를 수정하였습니다.");
		} else {
			out.println("해당 번호의 게시물이 없습니다.");
			out.flush();
		}
	}

}
