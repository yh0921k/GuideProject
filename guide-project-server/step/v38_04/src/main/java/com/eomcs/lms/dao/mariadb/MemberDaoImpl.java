package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDaoImpl implements MemberDao {

  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String sql = String.format(
          "insert into lms_member(name, email, pwd, tel, photo) values('%s', '%s', '%s', '%s', '%s')",
          member.getName(), member.getEmail(), member.getPassword(), member.getTel(),
          member.getPhoto());
      return stmt.executeUpdate(sql);
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("select member_id, name, email, pwd, tel, photo from lms_member");) {

      ArrayList<Member> list = new ArrayList<>();
      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        list.add(member);
      }
      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_id, name, email, pwd, tel, photo from lms_member where member_id="
                + no);) {

      if (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        return member;
      }
      return null;
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String sql = String.format(
          "update lms_member set name='%s', email='%s', pwd='%s', tel='%s', photo='%s' where member_id=%d",
          member.getName(), member.getEmail(), member.getPassword(), member.getTel(),
          member.getPhoto(), member.getNo());
      return stmt.executeUpdate(sql);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from lms_member where member_id=" + no);
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select member_id, name, email, pwd, tel, photo "
            + "from lms_member " + "where name like '%" + keyword + "%' or email like '%" + keyword
            + "%' or tel like '%" + keyword + "%'");) {

      ArrayList<Member> list = new ArrayList<>();
      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        list.add(member);
      }
      return list;
    }
  }
}
