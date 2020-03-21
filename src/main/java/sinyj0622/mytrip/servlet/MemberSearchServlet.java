package sinyj0622.mytrip.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;
import sinyj0622.util.Prompt;
import sinyj0622.util.RequestMapping;

@Component
public class MemberSearchServlet {

  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/search")
  public void service(Scanner in, PrintStream out) throws Exception {
    String response = Prompt.getString(in, out, "검색어? ");
    List<Member> members = memberService.findByKeyword(response);

    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getNickname(), m.getEmail(),
          m.getMyphoto(), m.getRegisteredDate());

      out.flush();
    }
  }

}
