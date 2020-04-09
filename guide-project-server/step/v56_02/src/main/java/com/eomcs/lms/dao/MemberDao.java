package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Member;

public interface MemberDao {
  public int insert(Member lesson) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNo(int no) throws Exception;


  public int update(Member lesson) throws Exception;

  public int delete(int no) throws Exception;

  List<Member> findByKeyword(String keyword) throws Exception;

  Member findByEmailAndPassword(Map<String, Object> params) throws Exception;
}
