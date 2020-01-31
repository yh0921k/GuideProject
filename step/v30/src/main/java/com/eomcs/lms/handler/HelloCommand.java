package com.eomcs.lms.handler;

import com.eomcs.util.Prompt;

public class HelloCommand implements Command {

  Prompt prompt;

  public HelloCommand(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    System.out.printf("%s님 안녕하세요.\n", prompt.inputString("이름 : "));
  }
}


