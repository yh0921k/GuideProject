package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.util.Prompt;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  public ClientApp() throws SQLException, ClassNotFoundException {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {
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

    String host = null;
    int port = 12345;
    String servletPath = null;
    try {
      if (!command.startsWith("guide://"))
        throw new Exception("명령어 형식이 옳지 않습니다.");

      String url = command.substring(8);

      int index = url.indexOf('/');
      String[] str = url.substring(0, index).split(":");
      host = str[0];
      if (str.length == 2) {
        port = Integer.parseInt(str[1]);
      }
      servletPath = url.substring(index);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try (Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      out.println(servletPath);
      out.flush();

      while (true) {
        String response = in.nextLine();
        if (response.equalsIgnoreCase("!end!"))
          break;
        System.out.println(response);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

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

  public static void main(String[] args)
      throws UnknownHostException, IOException, ClassNotFoundException, SQLException {
    System.out.println("클라이언트 수업 관리 시스템입니다.");
    ClientApp app = new ClientApp();
    app.service();
  }
}
