package com.eomcs.lms.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {
  protected String filename;
  protected List<T> list;

  public AbstractJsonFileDao(String filename) {
    list = new ArrayList<>();
    this.filename = filename;
    loadData();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void loadData() {
    File file = new File(filename);
    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      // 현재 클래스의 정보를 알아낸다.
      Class<?> currType = this.getClass(); // XXXJsonDao에서 호출됨, 즉 XXXJsonDao

      // generic type의 super class의 정보를 알아낸다.
      Type parentType = currType.getGenericSuperclass();

      // super class의 type 파라미터 중(위에 출력했을때 < > 사이의 값)에서 T 값을 추출한다.
      // >> super class가 제네릭이 적용된 경우 실제 타입은 다음과 같다.
      ParameterizedType parentType2 = (ParameterizedType) parentType;

      // >> 제네릭 super class 정보로부터 "타입 파라미터" 목록을 꺼낸다.
      // >> 예를 들어 suepr class가 다음과 같다면
      // >> class My<T, S, U, V> { ... }
      // >> 타입 파라미터 목록은 T, S, U, V 의 목록이다.
      // >> 그런데 AbstractJsonFileDao 클래스틑 타입 파라미터가 한 개이다.
      // >> 따라서 리턴되는 배열에는 타입 정보가 한 개 뿐이다.
      Type[] params = parentType2.getActualTypeArguments();

      // 여기서 관심있는 것은 T 타입 정보이다.
      // 배열의 0번 인덱스에 존재한다.
      Type itemType = params[0];
      System.out.println("actual type : " + itemType);

      // T가 실제 어떤 타입인지 알아냈다면, 이를 가지고 배열을 만든다.
      // 크기가 0인 배열을 생성한다.
      // 실제로 사용하려는 것이 아니라, 배열의 타입을 구하기 위함.
      T[] arr = (T[]) Array.newInstance((Class) itemType, 0);

      // T 타입의 배열 정보를 가지고 JSON 파일로부터 데이터를 읽는다.
      T[] dataArr = (T[]) new Gson().fromJson(in, arr.getClass());
      for (T b : dataArr) {
        list.add(b);
      }
      System.out.println(list.get(0));
      System.out.printf("%d 개의 객체를 로딩했습니다.\n", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생 : " + e.getMessage());
    }
  }

  protected void saveData() {
    File file = new File(filename);
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(list));
      System.out.printf("%d 개의 객체를 저장했습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중에 오류가 발생했습니다." + e.getMessage());
    }
  }

  protected abstract <K> int indexOf(K no);
}
