package com.eomcs.lms.service;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Lesson;

public interface LessonService {
  void add(Lesson lesson) throws Exception;

  List<Lesson> list() throws Exception;

  Lesson get(int no) throws Exception;

  List<Lesson> get(Map<String, Object> params) throws Exception;

  int delete(int no) throws Exception;

  int update(Lesson lesson) throws Exception;
}
