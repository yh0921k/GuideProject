package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.BoardHandler2;
import com.eomcs.lms.handler.CourseHandler;
import com.eomcs.lms.handler.UserHandler;
import java.util.Date;

public class App {
  public static void main(String[] args) throws Exception { 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    CourseHandler.br = br;
    UserHandler.br = br;
    BoardHandler.br = br;
    BoardHandler2.br = br;

    String command;
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/course/add":
          CourseHandler.addCourse();
          break;
        case "/course/list":
          CourseHandler.printCourseList();
          break;
        case "/user/add":
          UserHandler.addUser();
          break;
        case "/user/list":
          UserHandler.printUserList();
          break;
        case "/board/add":
          BoardHandler.addBoard();
          break;
        case "/board/list":
          BoardHandler.printBoardList();
          break;
        case "/board2/add":
          BoardHandler2.addBoard();
          break;
        case "/board2/list":
          BoardHandler2.printBoardList();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  } // end main



}
