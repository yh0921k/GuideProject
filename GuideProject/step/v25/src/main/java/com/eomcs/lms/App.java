package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Prompt;

public class App {
  
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();
  
  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    
    BoardHandler boardHandler = new BoardHandler(prompt, new LinkedList<Board>());
    LessonHandler lessonHandler = new LessonHandler(prompt, new ArrayList<Lesson>());
    MemberHandler memberHandler = new MemberHandler(prompt, new LinkedList<Member>());
    
    String command;
    
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      if(command.length() == 0)
        continue;
      
      commandStack.push(command);
      commandQueue.offer(command);
      switch (command) {
        case "/lesson/add":
          lessonHandler.addLesson();
          break;
        case "/lesson/list":
          lessonHandler.listLesson();
          break;
        case "/lesson/detail":
          lessonHandler.detailLesson();
          break;
        case "/lesson/update":
          lessonHandler.updateLesson();
          break;
        case "/lesson/delete":
          lessonHandler.deleteLesson();
          break;
        case "/member/add":
          memberHandler.addMember();
          break;
        case "/member/list":
          memberHandler.listMember();
          break;
        case "/member/detail":
          memberHandler.detailMember();
          break;
        case "/member/update":
          memberHandler.updateMember();
          break;
        case "/member/delete":
          memberHandler.deleteMember();
          break;
        case "/board/add":
          boardHandler.addBoard();
          break;
        case "/board/list":
          boardHandler.listBoard();
          break;
        case "/board/detail":
          boardHandler.detailBoard();
          break;  
        case "/board/update":
          boardHandler.updateBoard();
          break; 
        case "/board/delete":
          boardHandler.deleteBoard();
          break; 
        case "history":
          printCommandHistory(commandStack.iterator());
          break;
        case "history2":
          printCommandHistory(commandQueue.iterator());
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
      
    } while (!command.equalsIgnoreCase("quit"));
    
    System.out.println("안녕!");
    
    keyboard.close();
  }
  private static void printCommandHistory(Iterator<String> it) {
    int count = 0;
    while(it.hasNext()) {
      System.out.println(it.next());     
      if(++count % 5 == 0) {
        System.out.printf(": ");
        
        if(keyboard.nextLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }  
}






