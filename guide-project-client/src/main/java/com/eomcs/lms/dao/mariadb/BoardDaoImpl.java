package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  @Override
  public int insert(Board board) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "kyh", "1111");
        Statement stmt = con.createStatement();) {
      con.setAutoCommit(true);
      return stmt.executeUpdate("insert into lms_board(conts) values('" + board.getTitle() + "')");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "kyh", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select board_id, conts, cdt, vw_cnt from lms_board");) {

      ArrayList<Board> list = new ArrayList<>();
      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "kyh", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_id, conts, cdt, vw_cnt from lms_board where board_id=" + no);) {

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        return board;
      }
      return null;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "kyh", "1111");
        Statement stmt = con.createStatement();) {
      return stmt.executeUpdate(
          "update lms_board set conts='" + board.getTitle() + "' where board_id=" + board.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "kyh", "1111");
        Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from lms_board where board_id=" + no);
    }
  }
}
