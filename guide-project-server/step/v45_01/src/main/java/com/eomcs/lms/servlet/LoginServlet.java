package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.Prompt;

public class LoginServlet implements Servlet {

  MemberService memberService;

  public LoginServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "Email : ");
    String password = Prompt.getString(in, out, "password : ");

    Member member = memberService.get(email, password);
    if (member == null) {
      out.println("회원 정보가 유효하지 않습니다.");
      return;
    }
    System.out.println(member.getName());
    out.printf("%s님 환영합니다.\n", member.getName());
  }
}
