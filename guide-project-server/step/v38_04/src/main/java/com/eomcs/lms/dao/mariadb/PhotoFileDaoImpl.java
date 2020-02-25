package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  Connection con;

  public PhotoFileDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("insert into lms_photo_file(photo_id, file_path) values("
          + photoFile.getBoardNo() + ", '" + photoFile.getFilepath() + "')");
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select photo_file_id, photo_id, file_path from lms_photo_file where photo_id="
                + boardNo + " order by photo_file_id asc");) {

      ArrayList<PhotoFile> list = new ArrayList<>();
      while (rs.next()) {
        // 1)
        // PhotoFile photoFile = new PhotoFile();
        // photoFile.setNo(rs.getInt("photo_file_id"));
        // photoFile.setBoardNo(rs.getInt("photo_id"));
        // photoFile.setFilepath(rs.getString("file_path"));
        // list.add(photoFile);

        // 2)
        // list.add(new PhotoFile(rs.getInt("photo_file_id"), rs.getString("file_path"),
        // rs.getInt("photo_id")));

        // 3)
        list.add(new PhotoFile().setNo(rs.getInt("photo_file_id"))
            .setFilepath(rs.getString("file_path")).setBoardNo(rs.getInt("photo_id")));
      }
      return list;
    }
  }

  // @Override
  // public PhotoBoard findByNo(int no) throws Exception {
  // try (Statement stmt = con.createStatement();
  // ResultSet rs = stmt.executeQuery(
  // "select p.photo_id, p.titl, p.cdt, p.vw_cnt, l.lesson_id, l.titl lesson_title"
  // + " from lms_photo p inner join lms_lesson l on p.lesson_id=l.lesson_id"
  // + " where photo_id=" + no);) {
  //
  // if (rs.next()) {
  // PhotoBoard photoBoard = new PhotoBoard();
  // photoBoard.setNo(rs.getInt("photo_id"));
  // photoBoard.setTitle(rs.getString("titl"));
  // photoBoard.setCreatedDate(rs.getDate("cdt"));
  // photoBoard.setViewCount(rs.getInt("vw_cnt"));
  //
  // Lesson lesson = new Lesson();
  // lesson.setNo(rs.getInt("lesson_id"));
  // lesson.setTitle(rs.getString("lesson_title"));
  //
  // photoBoard.setLesson(lesson);
  // return photoBoard;
  // }
  // return null;
  // }
  //
  // }
  //
  // @Override
  // public int update(PhotoBoard photoBoard) throws Exception {
  // try (Statement stmt = con.createStatement();) {
  // return stmt.executeUpdate("update lms_photo set titl='" + photoBoard.getTitle()
  // + "' where photo_id=" + photoBoard.getNo());
  // }
  // }
  //
  @Override
  public int deleteAll(int baordNo) throws Exception {
    try (Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from lms_photo_file where photo_id=" + baordNo);
    }
  }
}
