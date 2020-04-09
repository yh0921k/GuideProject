package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@Component
public class LessonServiceImpl implements LessonService {

  LessonDao lessonDao;

  public LessonServiceImpl(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public Lesson get(int no) throws Exception {
    return lessonDao.findByNo(no);
  }

  @Override
  public void add(Lesson lesson) throws Exception {
    lessonDao.insert(lesson);
  }

  @Override
  public List<Lesson> list() throws Exception {
    return lessonDao.findAll();
  }

  @Override
  public int delete(int no) throws Exception {
    return lessonDao.delete(no);
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    return lessonDao.update(lesson);
  }

  @Override
  public List<Lesson> search(HashMap<String, Object> params) throws Exception {
    return lessonDao.findByKeyword(params);
  }
}
