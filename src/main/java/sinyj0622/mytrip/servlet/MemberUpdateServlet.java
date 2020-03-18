package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;

@Component("/member/update")
public class MemberUpdateServlet implements Servlet {

  MemberService memberService;

  public MemberUpdateServlet(MemberService memberService) {
    this.memberService = memberService;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    Member oldMember = memberService.get(no);
    Member newMember = new Member();

    if (oldMember == null) {
      out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    newMember.setNo(oldMember.getNo());
    newMember.setName(Prompt.getString(in, out, String.format("이름(%s)? ", oldMember.getName())));
    newMember
        .setNickname(Prompt.getString(in, out, String.format("별명(%s)? ", oldMember.getNickname())));
    newMember.setPassWord(Prompt.getString(in, out, "암호? ", oldMember.getPassWord()));
    newMember.setEmail(Prompt.getString(in, out, String.format("이메일(%s)? ", oldMember.getEmail())));
    newMember
        .setMyphoto(Prompt.getString(in, out, String.format("사진(%s)? ", oldMember.getMyphoto())));
    newMember.setPhonenumber(
        Prompt.getString(in, out, String.format("전화(%s)? ", oldMember.getPhonenumber())));


    if (memberService.update(newMember) > 0) {
      out.println("회원 정보를 수정하였습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
      out.flush();
    }
  }

}
