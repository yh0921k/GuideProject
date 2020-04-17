package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardDao {

  public int insert(PhotoBoard photoBoard) throws Exception;

  public List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception;

  public PhotoBoard findByNo(int no) throws Exception;

  public int update(PhotoBoard photoBoard) throws Exception;

  public int delete(int no) throws Exception;
}
