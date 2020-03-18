package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.sql.SqlSessionFactoryProxy;
import com.eomcs.util.ApplicationContext;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new LinkedHashSet<>();
  Map<String, Object> context = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();
  boolean serverStop = false;

  // IoC 컨테이너
  ApplicationContext iocContainer;

  // request handler mapper 준비
  RequestMappingHandlerMapping handlerMapper;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {
    notifyApplicationInitialized();

    // ApplicationContext(IoC 컨테이너)를 꺼낸다.
    iocContainer = (ApplicationContext) context.get("iocContainer");

    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) iocContainer.getBean("sqlSessionFactory");

    handlerMapper = (RequestMappingHandlerMapping) context.get("handlerMapper");

    try (ServerSocket serverSocket = new ServerSocket(12345)) {
      System.out.println("wait to connect with client...");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("Client connection complete");

        executorService.execute(() -> {
          processRequest(socket);
          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
          System.out.println("--------------------------------------------------");
        });

        if (serverStop) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("ServerSocket() : ");
      e.printStackTrace();
    }

    executorService.shutdown();
    while (true) {
      if (executorService.isTerminated())
        break;
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    notifyApplicationDestroyed();
    System.out.println("서버 종료");
  }

  void processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");


      String request = in.nextLine();
      System.out.println("Client : " + request);

      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }



      RequestHandler requestHandler = handlerMapper.getHandler(request);
      if (requestHandler != null) {
        try {
          // servlet.service(in, out);
          requestHandler.getMethod().invoke(requestHandler.getBean(), in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생");
          out.println(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생 : ");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }
      out.println("!end!");
      out.flush();
      System.out.println("클라이언트에게 응답하였음");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(PrintStream out) throws IOException {
    serverStop = true;
    out.println("OK");
    out.println("!end!");
    out.flush();
  }

  public static void main(String[] args) throws UnknownHostException, IOException {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }
}
