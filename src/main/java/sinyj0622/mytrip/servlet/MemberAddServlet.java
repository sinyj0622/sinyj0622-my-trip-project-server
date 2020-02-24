package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
	  Member member = new Member();
	  
	  out.println("이름? ");
	  out.println("!@#");
	  member.setName(in.nextLine());
	  
	  out.println("별명? ");
	  out.println("!@#");
	  member.setNickname(in.nextLine());
	  
	  out.println("암호? ");
	  out.println("!@#");
	  member.setPassWord(in.nextLine());
	  
	  out.println("이메일? ");
	  out.println("!@#");
	  member.setEmail(in.nextLine());
	  
	  out.println("사진? ");
	  out.println("!@#");
	  member.setMyphoto(in.nextLine());
	  
	  out.println("전화? ");
	  out.println("!@#");
	  member.setPhonenumber(in.nextLine());
	  
	  member.setRegisteredDate(new Date(System.currentTimeMillis()));
	  

    if (memberDao.insert(member) > 0) {
    	out.println("회원을 등록하였습니다.");
    } else {
      out.println("같은 번호의 회원이 있습니다.");

    }

  }

}
