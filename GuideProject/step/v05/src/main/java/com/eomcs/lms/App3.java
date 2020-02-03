package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class App3 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("번호 : ");
    int contentNum = Integer.parseInt(br.readLine());
    System.out.print("내용 : ");
    String contents = br.readLine();

    System.out.println("------------------------------");
    System.out.printf("번호 : %s\n", contentNum);
    System.out.printf("내용 : %s\n", contents);
    System.out.printf("작성일 : %s\n", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    //System.out.printf("작성일 : %s\n", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
    //System.out.printf("작성일 : %s\n", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    System.out.printf("조회수 : %d\n", 0);
    
    br.close();
  }
}
