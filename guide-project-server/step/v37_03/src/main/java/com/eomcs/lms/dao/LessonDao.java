package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {


  public int insert(Lesson lesson) throws Exception;

  public List<Lesson> findAll() throws Exception;

  public Lesson findByNo(int no) throws Exception;

  public int update(Lesson lesson) throws Exception;

  public int delete(int no) throws Exception;
}
