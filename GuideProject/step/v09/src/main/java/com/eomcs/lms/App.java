package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class App {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static class Course {
    int    courseNum;
    String courseName;
    String courseContent;
    Date   startDate;
    Date   endDate;
    int    totalHours;
    int    dayHours;
  }
  static class User {
    int    personNum;
    String personName;
    String PhoneNum;
    String picture;
    String passwd;
    String email;
    Date   joinDay;
  }
  static class Board {
    int    postNum;
    String postContent;
    Date   writeDay;
    int    viewCount;
  }
  static final int COURSE_SIZE = 100;
  static final int USER_SIZE   = 100;
  static final int BOARD_SIZE  = 100;
  static int courseCnt = 0;       
  static int userCnt   = 0;
  static int boardCnt  = 0;

  static Course[] courses = new Course[COURSE_SIZE];
  static User[]   users   = new User[USER_SIZE];
  static Board[]  boards  = new Board[BOARD_SIZE];
  static String   command;
  
  public static void main(String[] args) throws Exception { 
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/course/add":
          addCourse();
          break;
        case "/course/list":
          printCourseList();
          break;
        case "/user/add":
          addUser();
          break;
        case "/user/list":
          printUserList();
          break;
        case "/board/add":
          addBoard();
          break;
        case "/board/list":
          printBoardList();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  } // end main
  static void addCourse() throws Exception {
    Course c = new Course();          

    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("과목 번호 : ");
    c.courseNum = Integer.parseInt(br.readLine());
    System.out.printf("과목 이름 : ");
    c.courseName = br.readLine();
    System.out.printf("과목 내용 : ");
    c.courseContent = br.readLine();

    System.out.printf("시작일 : ");
    c.startDate = new Date(String.join("/", br.readLine().split("-")));
    System.out.printf("종료일 : ");
    c.endDate = new Date(String.join("/", br.readLine().split("-")));
    System.out.printf("총수업시간 : ");
    c.totalHours = Integer.parseInt(br.readLine());
    System.out.printf("하루수업시간 : ");
    c.dayHours = Integer.parseInt(br.readLine());

    courses[courseCnt++] = c;
    System.out.println("\nCourse Save Complete.");
  }
  static void printCourseList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%30s%4s%30s%30s%4s%30s%15s\n", "번호", " ", "과목", " ", " ", "기간", " ", "총시간");
    for(int i=0; i<courseCnt; i++) { 
      String start = new SimpleDateFormat("yyyy-MM-dd").format(courses[i].startDate);
      String end   = new SimpleDateFormat("yyyy-MM-dd").format(courses[i].endDate);
      System.out.printf("%4s%5s%-29s%10s ~ %10s%12s\n", 
          courses[i].courseNum, " ", courses[i].courseName, start, end, courses[i].totalHours);
    }
  }
  static void addUser() throws Exception {
    User u = new User();

    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    u.personNum = Integer.parseInt(br.readLine());
    System.out.printf("이름 : ");
    u.personName= br.readLine();
    System.out.printf("Email : ");
    u.email = br.readLine();

    System.out.printf("비밀번호 : ");
    u.passwd = br.readLine();
    System.out.printf("사진 : ");
    u.picture = br.readLine();
    System.out.printf("연락처 : ");
    u.PhoneNum = br.readLine();
    u.joinDay = new Date();
    users[userCnt++] = u;
    System.out.println("\nUser Save Complete.");
  }
  static void printUserList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%3s%5s%26s%5s%36s%6s\n", "번호", " ", "이름", " ", "연락처", " ", "가입일");
    for(int i=0; i<userCnt; i++) { 
      System.out.printf("%4d%5s%17s%13s%9s%6s\n", 
          users[i].personNum, users[i].personName, " ", users[i].PhoneNum, " " ,
          new SimpleDateFormat("yyyy-MM-dd").format(users[i].joinDay));
    }
  }
  static void addBoard() throws Exception {
    Board b = new Board();          
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    b.postNum = Integer.parseInt(br.readLine());
    System.out.printf("내용 : ");
    b.postContent = br.readLine();
    b.writeDay = new Date();
    b.viewCount = 0;
    boards[boardCnt++] = b;
    System.out.println("\nBoard Save Complete.");
  }
  static void printBoardList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    for(int i=0; i<boardCnt; i++) { 
      System.out.printf("%4d%4s%20s%10s%9s%8s\n", 
          boards[i].postNum, " ", boards[i].postContent, " ", 
          new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDay), boards[i].viewCount);
    }
  }
}
