package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class App3 {
  public static void main(String[] args) throws Exception {
    /* 변수 정의 영역 */ 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    int arraySize = 100; 
    int index = 0;       

    String[] postContents = new String[arraySize]; 
    Date[] writeDay       = new Date[arraySize];
    int[] postCount       = new int[arraySize];
    int[] postNum         = new int[arraySize];

    /* 입력부 */
    while(true) { 
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      postNum[index] = Integer.parseInt(br.readLine());
      System.out.printf("내용 : ");
      postContents[index] = br.readLine();
      writeDay[index] = new Date();
      postCount[index] = 0;
      
      System.out.printf("계속 입력하시겠습니까? (Y/n) : ");
      index++; 
      String tmp = br.readLine();
      if(tmp.equalsIgnoreCase("y")) 
        continue;
      else if(tmp.equalsIgnoreCase("n")) 
        break;
      else {
        System.out.println("잘못 입력하셨습니다.\n프로그램을 종료합니다.\n");
        return;
      }
    }

    /* 출력부 */ 
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    for(int j=0; j<index; j++) { 
      System.out.printf("%4d%4s%20s%10s%9s%8s\n", postNum[j], " ", postContents[j], " ", new SimpleDateFormat("yyyy-MM-dd").format(writeDay[j]), postCount[j]);
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
  }
}
