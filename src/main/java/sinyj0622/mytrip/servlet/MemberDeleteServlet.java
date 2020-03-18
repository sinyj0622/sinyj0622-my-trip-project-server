package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import sinyj0622.mytrip.service.MemberService;
import sinyj0622.util.Component;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class MemberDeleteServlet {

  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }


  @RequestMapping("/member/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (memberService.delete(no) > 0) {
      out.println("해당 회원 데이터를 삭제하였습니다.");
    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }

}
