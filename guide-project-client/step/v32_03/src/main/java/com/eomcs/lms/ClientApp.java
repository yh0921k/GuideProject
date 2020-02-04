package com.eomcs.lms;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.handler.Command;
import com.eomcs.util.Prompt;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {
    HashMap<String, Command> commandMap = new HashMap<>();
    String command;

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    while (true) {
      command = prompt.inputString("\n명령>");
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

    // String serverAddr = null;
    // int port = 0;
    // Scanner keyScan = new Scanner(System.in);
    // try {
    // System.out.printf("서버 IP : ");
    // serverAddr = keyScan.nextLine();
    // System.out.printf("Port : ");
    // port = Integer.parseInt(keyScan.nextLine());
    //
    // } catch (Exception e) {
    // System.out.println("유효하지 않은 서버 주소 또는 포트 번호");
    // e.printStackTrace();
    // keyScan.close();
    // return;
    // }
    //
    // try (Socket socket = new Socket(serverAddr, port);
    // Scanner in = new Scanner(socket.getInputStream());
    // PrintStream out = new PrintStream(socket.getOutputStream())) {
    //
    // System.out.println("Connection complete");
    // System.out.printf("Message : ");
    // String sendMsg = keyScan.nextLine();
    //
    // out.println(sendMsg);
    // System.out.println("Send complete");
    //
    // String message = in.nextLine();
    // System.out.println("Server : " + message);
    // System.out.println("Receive complete");
    //
    // System.out.println("Close connection");
    // } catch (Exception e) {
    // System.out.println("Exception occurs");
    // e.printStackTrace();
    // }
    // keyScan.close();
  }
}
