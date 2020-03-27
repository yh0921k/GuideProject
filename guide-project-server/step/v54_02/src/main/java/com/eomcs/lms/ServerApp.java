package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
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

      String servletPath = getServletPath(requestUri);
      logger.debug(String.format("servlet path => %s", servletPath));

      Map<String, String> params = getParameters(requestUri);

      printResponseHeader(out);

      if (servletPath.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      RequestHandler requestHandler = handlerMapper.getHandler(servletPath);
      if (requestHandler != null) {
        try {
          // servlet.service(in, out);
          requestHandler.getMethod().invoke(requestHandler.getBean(), params, out);

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
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>실행 오류!</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>실행 오류!</h1>");
    out.println("<p>요청한 명령을 처리할 수 없습니다.</p>");
    out.println("</body>");
    out.println("</html>");
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

  private String getServletPath(String requestUri) {
    // requestUri => /member/add?email=aaa@test.com&name=aaa&password=1111
    return requestUri.split("\\?")[0]; // 예) /member/add
  }

  private Map<String, String> getParameters(String requestUri) throws UnsupportedEncodingException {
    // 데이터(Query String)는 따로 저장
    // => /member/list?email=aaa@test.com&name=aaa&password=1111
    Map<String, String> params = new HashMap<>();
    String[] items = requestUri.split("\\?");
    if (items.length > 1) {
      logger.debug(String.format("query string => %s", items[1]));
      String[] entries = items[1].split("&");
      for (String entry : entries) {
        logger.debug(String.format("parameter => %s", entry));
        String[] kv = entry.split("=");
        if (kv.length > 1) {
          // 웹브라우저가 URL 인코딩하여 보낸 데이터를
          // 디코딩하여 String 객체로 만든다.
          String value = URLDecoder.decode(kv[1], "UTF-8");

          params.put(kv[0], value);
        } else {
          params.put(kv[0], "");
        }
      }
    }
    return params;
  }

  public static void main(String[] args) throws UnknownHostException, IOException {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }
}
