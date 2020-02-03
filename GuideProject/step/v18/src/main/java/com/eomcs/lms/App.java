package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.CourseHandler;
import com.eomcs.lms.handler.UserHandler;

public class App {
  public static void main(String[] args) throws Exception { 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    CourseHandler courseHandler = new CourseHandler(br);
    UserHandler userHandler = new UserHandler(br);
    BoardHandler boardHandler = new BoardHandler(br);    

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
          boardHandler.addBoard();
          break;
        case "/board/list":
          boardHandler.printBoardList();
          break;
        case "/board/detail":
          boardHandler.printDetailBoard();
          break;        
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  } // end main



}
