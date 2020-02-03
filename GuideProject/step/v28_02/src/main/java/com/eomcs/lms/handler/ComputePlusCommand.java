package com.eomcs.lms.handler;

import com.eomcs.util.Prompt;

public class ComputePlusCommand implements Command {

  Prompt prompt;

  public ComputePlusCommand(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    int number1 = prompt.inputInt("숫자 1 : ");
    int number2 = prompt.inputInt("숫자 2 : ");
    System.out.printf("계산 결과는 %d 입니다.\n", number1 + number2);
  }
}


