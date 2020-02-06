package com.eomcs.lms.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDetailCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public BoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      out.writeUTF("/board/detail");
      out.writeInt(prompt.inputInt("번호 : "));
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      Board board = (Board) in.readObject();
      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("등록일: %s\n", board.getDate());
      System.out.printf("조회수: %d\n", board.getViewCount());
      System.out.printf("작성자 : %s\n", board.getWriter());

    } catch (Exception e) {
      System.out.println("Communication Error Occurs");
    }
  }
}


