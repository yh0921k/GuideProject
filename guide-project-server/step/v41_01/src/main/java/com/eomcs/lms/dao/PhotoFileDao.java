package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoFile;

public interface PhotoFileDao {

  public int insert(PhotoFile photoBoard) throws Exception;

  public List<PhotoFile> findAll(int boardNo) throws Exception;

  public int deleteAll(int boardNo) throws Exception;
}
