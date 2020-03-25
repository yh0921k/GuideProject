package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ServerApp {

  static Logger logger = LogManager.getLogger(ServerApp.class);

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

    handlerMapper = (RequestMappingHandlerMapping) context.get("handlerMapper");

    try (ServerSocket serverSocket = new ServerSocket(12345)) {
      logger.info("wait to connect with client...");
      while (true) {
        Socket socket = serverSocket.accept();
        logger.info("Client connection complete");

        executorService.execute(() -> {
          processRequest(socket);
          logger.info("--------------------------------------------------");
        });

        if (serverStop) {
          break;
        }
      }
    } catch (Exception e) {
      logger.error("ServerSocket() : " + e.getMessage());
      // e.printStackTrace();
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
    logger.info("서버 종료");
  }

  void processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String[] requestLine = in.nextLine().split(" ");
      while (true) {
        String line = in.nextLine();
        if (line.length() == 0) {
          break;
        }
      }

      String method = requestLine[0];
      String requestUri = requestLine[1];
      logger.info("method : " + method);
      logger.info("requestUri : " + requestUri);

      logger.info("통신을 위한 입출력 스트림을 준비하였음!");

      printResponseHeader(out);

      if (requestUri.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      RequestHandler requestHandler = handlerMapper.getHandler(requestUri);
      if (requestHandler != null) {
        try {
          // servlet.service(in, out);
          requestHandler.getMethod().invoke(requestHandler.getBean(), in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생");
          out.println(e.getMessage());

          logger.info("클라이언트 요청 처리 중 오류 발생 : ");
          logger.info(e.getMessage());
          StringWriter strWriter = new StringWriter();
          e.printStackTrace(new PrintWriter(strWriter));
          logger.debug(strWriter.toString());
        }
      } else {
        notFound(out);
        logger.info("해당 명령을 처리할 수 없습니다.");
      }
      out.flush();
      logger.info("클라이언트에게 응답하였음");

    } catch (Exception e) {
      logger.error("예외 발생:" + e.getMessage());
      StringWriter strWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(strWriter));
      logger.debug(strWriter.toString());
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

  private void printResponseHeader(PrintStream out) {
    out.println("HTTP/1.1 200 OK");
    out.println("Server : bitcampServer");
    out.println();
  }

  public static void main(String[] args) throws UnknownHostException, IOException {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }
}
