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
import com.eomcs.lms.dao.proxy.BoardDaoProxy;
import com.eomcs.lms.dao.proxy.LessonDaoProxy;
import com.eomcs.lms.dao.proxy.MemberDaoProxy;
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

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String host;
  int port;

  public ClientApp() {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {
    try {
      if ((host = prompt.inputString("서버 IP(enter) : ")).equals("")) {
        host = "127.0.0.1";
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

    String command;
    while (true) {
      command = prompt.inputString("\n명령>");
      if (command.length() == 0)
        continue;

      if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);
      System.out.println("--------------------------------------------------");
    }
    keyboard.close();
  }

  private void processCommand(String command) {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("Connection success");

      BoardDaoProxy boardDao = new BoardDaoProxy(in, out);
      LessonDaoProxy lessonDao = new LessonDaoProxy(in, out);
      MemberDaoProxy memberDao = new MemberDaoProxy(in, out);

      HashMap<String, Command> commandMap = new HashMap<>();

      commandMap.put("/board/list", new BoardListCommand(boardDao));
      commandMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
      commandMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
      commandMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
      commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));

      commandMap.put("/member/list", new MemberListCommand(memberDao));
      commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
      commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
      commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
      commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));

      commandMap.put("/lesson/list", new LessonListCommand(lessonDao));
      commandMap.put("/lesson/add", new LessonAddCommand(lessonDao, prompt));
      commandMap.put("/lesson/detail", new LessonDetailCommand(lessonDao, prompt));
      commandMap.put("/lesson/update", new LessonUpdateCommand(lessonDao, prompt));
      commandMap.put("/lesson/delete", new LessonDeleteCommand(lessonDao, prompt));

      commandMap.put("/server/stop", () -> {
        try {
          out.writeUTF(command);
          out.flush();
          System.out.println("Server : " + in.readUTF());
          System.out.println("안녕!");
        } catch (Exception e) {
        }
      });

      Command commandHandler = commandMap.get(command);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        return;
      }
      commandHandler.execute();
    } catch (Exception e) {
      System.out.printf("[processCommand()] : %s\n", e.getMessage());
      e.printStackTrace();
      System.out.println("--------------------------------------------------");
    }
    System.out.println("Connection close");
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
