package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
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
	  

    if (memberDao.insert(member) > 0) {
    	out.println("회원을 등록하였습니다.");
    } else {
      out.println("같은 번호의 회원이 있습니다.");

    }

  }

}
