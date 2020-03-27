package com.eomcs.lms.service;

import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonService {
  void add(Lesson lesson) throws Exception;

  List<Lesson> list() throws Exception;

  Lesson get(int no) throws Exception;


  int delete(int no) throws Exception;

  int update(Lesson lesson) throws Exception;

  List<Lesson> search(HashMap<String, Object> params) throws Exception;
}
