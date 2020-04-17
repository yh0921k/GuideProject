package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMappingHandlerMapping;

@WebServlet("/app/*")
@MultipartConfig(maxFileSize = 10000000)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static Logger logger = LogManager.getLogger(DispatcherServlet.class);
  RequestMappingHandlerMapping handlerMapper = null;

  String viewUrl = null;

  @Override
  public void init() throws ServletException {
    handlerMapper =
        (RequestMappingHandlerMapping) getServletContext().getAttribute("handlerMapper");
    logger.debug("init() 호출됨");
    logger.debug(handlerMapper);
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String pathInfo = request.getPathInfo();
      response.setContentType("text/html;charset=UTF-8");

      ArrayList<Cookie> cookies = new ArrayList<>();
      request.setAttribute("cookies", cookies);

      RequestHandler requestHandler = handlerMapper.getHandler(pathInfo);
      if (requestHandler != null) {
        try {
          viewUrl = (String) requestHandler.getMethod().invoke(requestHandler.getBean(), request,
              response);
        } catch (Exception e) {
          StringWriter out = new StringWriter();
          e.printStackTrace(new PrintWriter(out));
          request.setAttribute("errorDetail", out.toString());
          request.getRequestDispatcher("/error.jsp").forward(request, response);
          return;
        }
      } else {
        logger.info("해당 명령을 지원하지 않습니다.");
        throw new Exception("해당 명령을 지원하지 않습니다.");
      }

      if (cookies.size() > 0) {
        for (Cookie cookie : cookies) {
          response.addCookie(cookie);
        }
      }

      String refreshUrl = (String) request.getAttribute("refreshUrl");
      if (refreshUrl != null) {
        response.setHeader("Refresh", refreshUrl);
      }

      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).include(request, response);
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
