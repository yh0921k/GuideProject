package com.eomcs.lms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.ComputePlusCommand;
import com.eomcs.lms.handler.HelloCommand;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.util.Prompt;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();
  static LinkedList<Board> boardList = new LinkedList<>();
  static LinkedList<Lesson> lessonList = new LinkedList<>();
  static LinkedList<Member> memberList = new LinkedList<>();

  public static void main(String[] args) {

    loadLessonData();
    loadMemberData();
    loadBoardData();

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/board/add", new BoardAddCommand(prompt, boardList));
    commandMap.put("/board/list", new BoardListCommand(boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));

    commandMap.put("/lesson/add", new LessonAddCommand(prompt, lessonList));
    commandMap.put("/lesson/list", new LessonListCommand(lessonList));
    commandMap.put("/lesson/detail", new LessonDetailCommand(prompt, lessonList));
    commandMap.put("/lesson/update", new LessonUpdateCommand(prompt, lessonList));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(prompt, lessonList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));

    commandMap.put("/hello", new HelloCommand(prompt));
    commandMap.put("/compute/plus", new ComputePlusCommand(prompt));

    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);
      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("--------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생 : %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();

    saveLessonData();
    saveMemberData();
    saveBoardData();
  }

  private static void printCommandHistory(Iterator<String> it) {
    int count = 0;
    while (it.hasNext()) {
      System.out.println(it.next());
      if (++count % 5 == 0) {
        System.out.printf(": ");

        if (keyboard.nextLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private static void loadLessonData() {
    File file = new File("./lesson.csv");
    FileReader in = null;
    Scanner dataScan = null;
    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;
      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          Lesson lesson = new Lesson();
          lesson.setNo(Integer.parseInt(data[0]));
          lesson.setTitle(data[1]);
          lesson.setDescription(data[2]);
          lesson.setStartDate(Date.valueOf(data[3]));
          lesson.setEndDate(Date.valueOf(data[4]));
          lesson.setTotalHours(Integer.parseInt(data[5]));
          lesson.setDayHours(Integer.parseInt(data[6]));
          lessonList.add(lesson);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("%d 개의 수업 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생 : " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveLessonData() {
    File file = new File("./lesson.csv");
    FileWriter out = null;
    try {
      out = new FileWriter(file);
      int count = 0;
      for (Lesson lesson : lessonList) {
        String line = String.format("%d,%s,%s,%s,%s,%d,%d\n", lesson.getNo(), lesson.getTitle(),
            lesson.getDescription(), lesson.getStartDate(), lesson.getEndDate(),
            lesson.getTotalHours(), lesson.getDayHours());
        out.write(line);
        count++;
      }
      System.out.printf("%d 개의 수업 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중에 오류가 발생했습니다." + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }

  private static void loadMemberData() {
    File file = new File("./member.csv");
    FileReader in = null;
    Scanner dataScan = null;
    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;
      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          Member member = new Member();
          member.setNo(Integer.parseInt(data[0]));
          member.setName(data[1]);
          member.setEmail(data[2]);
          member.setPassword(data[3]);
          member.setPhoto(data[4]);
          member.setTel(data[5]);
          member.setRegisteredDate(Date.valueOf(data[6]));
          memberList.add(member);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생 : " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveMemberData() {
    File file = new File("./member.csv");
    FileWriter out = null;
    try {
      out = new FileWriter(file);
      int count = 0;
      for (Member member : memberList) {
        String line = String.format("%d,%s,%s,%s,%s,%s,%s\n", member.getNo(), member.getName(),
            member.getEmail(), member.getPassword(), member.getPhoto(), member.getTel(),
            member.getRegisteredDate());
        out.write(line);
        count++;
      }
      System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중에 오류가 발생했습니다." + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.csv");
    FileReader in = null;
    Scanner dataScan = null;
    try {
      in = new FileReader(file);
      dataScan = new Scanner(in);
      int count = 0;
      while (true) {
        try {
          String line = dataScan.nextLine();
          String[] data = line.split(",");

          Board board = new Board();
          board.setNo(Integer.parseInt(data[0]));
          board.setTitle(data[1]);
          board.setDate(Date.valueOf(data[2]));
          board.setViewCount(Integer.parseInt(data[3]));
          board.setWriter(data[4]);
          boardList.add(board);
          count++;
        } catch (Exception e) {
          break;
        }
      }
      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생 : " + e.getMessage());
    } finally {
      try {
        dataScan.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private static void saveBoardData() {
    File file = new File("./board.csv");
    FileWriter out = null;
    try {
      out = new FileWriter(file);
      int count = 0;
      for (Board board : boardList) {
        String line = String.format("%d,%s,%s,%d,%s\n", board.getNo(), board.getTitle(),
            board.getDate(), board.getViewCount(), board.getWriter());
        out.write(line);
        count++;
      }
      System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", count);
    } catch (IOException e) {
      System.out.println("파일 쓰기 중에 오류가 발생했습니다." + e.getMessage());
    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }
}


