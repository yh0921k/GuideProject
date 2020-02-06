package com.eomcs.lms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberFileDao {
  String filename;
  List<Member> list;

  public MemberFileDao(String filename) {
    list = new ArrayList<>();
    this.filename = filename;
    loadData();
  }

  @SuppressWarnings("unchecked")
  private void loadData() {
    File file = new File("./data/member.ser2");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      list = (List<Member>) in.readObject();
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생 : " + e.getMessage());
    }
  }

  private void saveData() {
    File file = new File("./data/member.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(list);
      System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중에 오류가 발생했습니다." + e.getMessage());
    }
  }

  public int insert(Member member) throws Exception {
    if (indexOf(member.getNo()) > -1) {
      return 0;
    }

    list.add(member);
    saveData();
    return 1;
  }

  public List<Member> findAll() throws Exception {
    return list;
  }

  public Member findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1)
      return null;
    return list.get(index);
  }

  public int update(Member member) throws Exception {
    int index = indexOf(member.getNo());
    if (index == -1)
      return 0;

    list.set(index, member);
    saveData();
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1)
      return 0;

    list.remove(index);
    saveData();
    return 1;
  }

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
