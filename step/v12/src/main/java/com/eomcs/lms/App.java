package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.BoardHandler2;
import com.eomcs.lms.handler.BoardHandler3;
import com.eomcs.lms.handler.BoardHandler4;
import com.eomcs.lms.handler.BoardHandler5;
import com.eomcs.lms.handler.BoardHandler6;
import com.eomcs.lms.handler.CourseHandler;
import com.eomcs.lms.handler.UserHandler;

public class App {
  public static void main(String[] args) throws Exception { 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    CourseHandler.br = br;
    UserHandler.br = br;
    BoardHandler.br = br;
    BoardHandler2.br = br;
    BoardHandler3.br = br;
    BoardHandler4.br = br;
    BoardHandler5.br = br;
    BoardHandler6.br = br;

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
        case "/board/detail":
          BoardHandler.printDetailBoard();
          break;
        case "/board2/add":
          BoardHandler2.addBoard();
          break;
        case "/board2/list":
          BoardHandler2.printBoardList();
          break;
        case "/board2/detail":
          BoardHandler2.printDetailBoard();
          break;
        case "/board3/add":
          BoardHandler3.addBoard();
          break;
        case "/board3/list":
          BoardHandler3.printBoardList();
          break;
        case "/board3/detail":
          BoardHandler3.printDetailBoard();
          break;
        case "/board4/add":
          BoardHandler4.addBoard();
          break;
        case "/board4/list":
          BoardHandler4.printBoardList();
          break;
        case "/board4/detail":
          BoardHandler4.printDetailBoard();
          break;
        case "/board5/add":
          BoardHandler5.addBoard();
          break;
        case "/board5/list":
          BoardHandler5.printBoardList();
          break;
        case "/board5/detail":
          BoardHandler5.printDetailBoard();
          break;
        case "/board6/add":
          BoardHandler6.addBoard();
          break;
        case "/board6/list":
          BoardHandler6.printBoardList();
          break;
        case "/board6/detail":
          BoardHandler6.printDetailBoard();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  } // end main



}
