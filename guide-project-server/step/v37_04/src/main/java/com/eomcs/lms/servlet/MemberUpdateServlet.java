package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호 : \n!{}!");
    int no = Integer.parseInt(in.nextLine());

    Member oldMember;
    oldMember = memberDao.findByNo(no);
    if (oldMember == null) {
      out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    Member newMember = new Member();
    newMember.setNo(oldMember.getNo());
    out.printf("이름(%s) : \n!{}!\n", oldMember.getName());
    newMember.setName(in.nextLine());
    out.printf("이메일(%s) : \n!{}!\n", oldMember.getEmail());
    newMember.setEmail(in.nextLine());
    out.printf("암호(%s) : \n!{}!\n", oldMember.getPassword());
    newMember.setPassword(in.nextLine());
    out.printf("사진(%s) : \n!{}!\n", oldMember.getPhoto());
    newMember.setPhoto(in.nextLine());
    out.printf("전화(%s) : \n!{}!\n", oldMember.getTel());
    newMember.setTel(in.nextLine());

    if (memberDao.update(newMember) > 0) {
      out.println("회원을 변경했습니다.");

    } else {
      out.println("회원 업데이트 실패");
    }
  }
}
