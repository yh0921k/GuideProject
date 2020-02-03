package com.eomcs.lms;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
public class App2 {
  public static void main(String[] args) throws Exception {
    /* 변수 정의 영역 */ 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    int arraySize = 100; 
    int index = 0;       

    String[] personName = new String[arraySize]; 
    String[] phoneNum   = new String[arraySize];
    String[] picture    = new String[arraySize];
    String[] passwd     = new String[arraySize];
    String[] email      = new String[arraySize];
    Date[] joinDay      = new Date[arraySize];
    int[] personNum     = new int[arraySize];  

    /* 입력부 */
    while(true) { 
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      personNum[index] = Integer.parseInt(br.readLine());
      System.out.printf("이름 : ");
      personName[index] = br.readLine();
      System.out.printf("Email : ");
      email[index] = br.readLine();
      
      System.out.printf("비밀번호 : ");
      passwd[index] = br.readLine();
      System.out.printf("사진 : ");
      picture[index] = br.readLine();
      System.out.printf("연락처 : ");
      phoneNum[index] = br.readLine();
      joinDay[index] = new Date();
      
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
    System.out.printf("%4s%3s%5s%26s%5s%36s%6s\n", "번호", " ", "이름", " ", "연락처", " ", "가입일");
    for(int j=0; j<index; j++) { 
      System.out.printf("%4d%5s%17s%13s%9s%6s\n", personNum[j], personName[j], " ", phoneNum[j], " " , new SimpleDateFormat("yyyy-MM-dd").format(joinDay[j]));
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
  }

}
