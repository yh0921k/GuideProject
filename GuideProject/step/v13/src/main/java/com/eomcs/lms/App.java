package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.CourseHandler;
import com.eomcs.lms.handler.UserHandler;

public class App {
  public static void main(String[] args) throws Exception { 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    CourseHandler.br = br;
    UserHandler.br = br;
    BoardHandler.br = br;

    CourseHandler courseHandler = new CourseHandler();
    UserHandler userHandler = new UserHandler();
    BoardHandler boardHandler1 = new BoardHandler();
    BoardHandler boardHandler2 = new BoardHandler();
    BoardHandler boardHandler3 = new BoardHandler();
    BoardHandler boardHandler4 = new BoardHandler();
    BoardHandler boardHandler5 = new BoardHandler();
    BoardHandler boardHandler6 = new BoardHandler();

    String command;
    
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/course/add":
          courseHandler.addCourse();
          break;
        case "/course/list":
          courseHandler.printCourseList();
          break;
        case "/user/add":
          userHandler.addUser();
          break;
        case "/user/list":
          userHandler.printUserList();
          break;
        case "/board/add":
          boardHandler1.addBoard();
          break;
        case "/board/list":
          boardHandler1.printBoardList();
          break;
        case "/board/detail":
          boardHandler1.printDetailBoard();
          break;
        case "/board2/add":
          boardHandler2.addBoard();
          break;
        case "/board2/list":
          boardHandler2.printBoardList();
          break;
        case "/board2/detail":
          boardHandler2.printDetailBoard();
          break;
        case "/board3/add":
          boardHandler3.addBoard();
          break;
        case "/board3/list":
          boardHandler3.printBoardList();
          break;
        case "/board3/detail":
          boardHandler3.printDetailBoard();
          break;
        case "/board4/add":
          boardHandler4.addBoard();
          break;
        case "/board4/list":
          boardHandler4.printBoardList();
          break;
        case "/board4/detail":
          boardHandler4.printDetailBoard();
          break;
        case "/board5/add":
          boardHandler5.addBoard();
          break;
        case "/board5/list":
          boardHandler5.printBoardList();
          break;
        case "/board5/detail":
          boardHandler5.printDetailBoard();
          break;
        case "/board6/add":
          boardHandler6.addBoard();
          break;
        case "/board6/list":
          boardHandler6.printBoardList();
          break;
        case "/board6/detail":
          boardHandler6.printDetailBoard();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  } // end main



}
