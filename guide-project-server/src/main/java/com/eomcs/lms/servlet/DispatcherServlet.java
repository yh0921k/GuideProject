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

@WebServlet("/app/*")
@MultipartConfig(maxFileSize = 10000000)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String pathInfo = request.getPathInfo();
    response.setContentType("text/html;charset=UTF-8");

    ArrayList<Cookie> cookies = new ArrayList<>();
    request.setAttribute("cookies", cookies);

    request.getRequestDispatcher(pathInfo).include(request, response);

    if (cookies.size() > 0) {
      for (Cookie cookie : cookies) {
        response.addCookie(cookie);
      }
    }

    if (request.getAttribute("error") != null) {
      Exception error = (Exception) request.getAttribute("error");
      StringWriter out = new StringWriter();
      error.printStackTrace(new PrintWriter(out));
      request.setAttribute("errorDetail", out.toString());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }

    String refreshUrl = (String) request.getAttribute("refreshUrl");
    if (refreshUrl != null) {
      response.setHeader("Refresh", refreshUrl);
    }

    String viewUrl = (String) request.getAttribute("viewUrl");
    if (viewUrl.startsWith("redirect:")) {
      response.sendRedirect(viewUrl.substring(9));
    } else {
      request.getRequestDispatcher(viewUrl).include(request, response);
    }
  }

}
