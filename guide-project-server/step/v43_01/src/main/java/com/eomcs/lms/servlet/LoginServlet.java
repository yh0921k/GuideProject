package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Prompt;

public class LoginServlet implements Servlet {

  MemberDao memberDao;

  public LoginServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "Email : ");
    String password = Prompt.getString(in, out, "password : ");

    Member member = memberDao.findByEmailAndPassword(email, password);
    if (member == null) {
      out.println("회원 정보가 유효하지 않습니다.");
      return;
    }
    System.out.println(member.getName());
    out.printf("%s님 환영합니다.\n", member.getName());
  }
}
