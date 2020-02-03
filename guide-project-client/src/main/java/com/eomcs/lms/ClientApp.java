package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientApp {
  public static void main(String[] args) throws UnknownHostException, IOException {
    System.out.println("클라이언트 수업 관리 시스템입니다.");
    String serverAddr = null;
    int port = 0;
    Scanner keyScan = new Scanner(System.in);
    try {
      System.out.printf("서버 IP : ");
      serverAddr = keyScan.nextLine();
      System.out.printf("Port : ");
      port = Integer.parseInt(keyScan.nextLine());

    } catch (Exception e) {
      System.out.println("유효하지 않은 서버 주소 또는 포트 번호");
      e.printStackTrace();
      keyScan.close();
      return;
    }

    try (Socket socket = new Socket(serverAddr, port);
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("Connection complete");
      System.out.printf("Message : ");
      String sendMsg = keyScan.nextLine();

      out.println(sendMsg);
      System.out.println("Send complete");

      String message = in.nextLine();
      System.out.println("Server : " + message);
      System.out.println("Receive complete");

      System.out.println("Close connection");
    } catch (Exception e) {
      System.out.println("Exception occurs");
      e.printStackTrace();
    }
    keyScan.close();
  }
}
