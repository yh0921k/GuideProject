package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
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

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {

    String serverAddr = null;
    int port = 0;
    try {
      if ((serverAddr = prompt.inputString("서버 IP(enter) : ")).equals("")) {
        serverAddr = "127.0.0.1";
      }
      try {
        port = prompt.inputInt("Port(enter) : ");
      } catch (Exception e) {
        port = 12345;
      }

    } catch (Exception e) {
      System.out.println("Invalid Address or Port");
      e.printStackTrace();
      keyboard.close();
      return;
    }

    try (Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("Connection success");
      processCommand(out, in);
      System.out.println("Connection close");

    } catch (Exception e) {
      System.out.println("service() : ");
      e.printStackTrace();
    }
    keyboard.close();
  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/board/list", new BoardListCommand(out, in));
    commandMap.put("/board/add", new BoardAddCommand(out, in, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(out, in, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(out, in, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(out, in, prompt));
    commandMap.put("/member/list", new MemberListCommand(out, in));
    commandMap.put("/member/add", new MemberAddCommand(out, in, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(out, in, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(out, in, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(out, in, prompt));
    commandMap.put("/lesson/list", new LessonListCommand(out, in));
    commandMap.put("/lesson/add", new LessonAddCommand(out, in, prompt));
    commandMap.put("/lesson/detail", new LessonDetailCommand(out, in, prompt));
    commandMap.put("/lesson/update", new LessonUpdateCommand(out, in, prompt));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(out, in, prompt));
    String command;

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    try {
      while (true) {
        command = prompt.inputString("\n명령>");
        if (command.length() == 0)
          continue;

        if (command.equals("quit") || command.equals("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("Server : " + in.readUTF());
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
          System.out.println("실행할 수 없는 령입니다.");
        }
      }
    } catch (Exception e) {
      System.out.println("Runtime exception occurs");
    }
    keyboard.close();
  }

  private void printCommandHistory(Iterator<String> it) {
    int count = 0;
    while (it.hasNext()) {
      System.out.println(it.next());
      if (++count % 5 == 0) {
        if (prompt.inputString(":").equalsIgnoreCase("q"))
          break;
      }
    }
  }

  public static void main(String[] args) throws UnknownHostException, IOException {
    System.out.println("클라이언트 수업 관리 시스템입니다.");

    ClientApp app = new ClientApp();
    app.service();



  }
}
