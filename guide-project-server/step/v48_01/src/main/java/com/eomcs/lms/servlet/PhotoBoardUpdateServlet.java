package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class PhotoBoardUpdateServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/update")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호 : ");

    PhotoBoard old = photoBoardService.get(no);
    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNo(no);
    newPhotoBoard.setTitle(
        Prompt.getString(in, out, String.format("제목(%s) : ", old.getTitle()), old.getTitle()));

    printPhotoFiles(out, old);
    out.println();
    out.println("사진은 일부만 변경할 수 없습니다.");
    out.println("전체를 새로 등록해야 합니다.");

    String response = Prompt.getString(in, out, "사진을 변경하시겠습니까?(Y/n)");
    if (response.equalsIgnoreCase("y")) {
      newPhotoBoard.setFiles(inputPhotoFiles(in, out));
    } else {
      newPhotoBoard.setFiles(null);
    }

    photoBoardService.update(newPhotoBoard);
    out.println("사진 게시글을 변경했습니다.");
  }

  private void printPhotoFiles(PrintStream out, PhotoBoard photoBoard) throws Exception {
    out.println("사진 파일 : ");
    List<PhotoFile> oldPhotoFiles = photoBoard.getFiles();
    for (PhotoFile photoFile : oldPhotoFiles) {
      out.printf("> %s\n", photoFile.getFilepath());
    }
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 엔터를 입력하면 파일 추가를 마칩니다.");
    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    while (true) {
      String filepath = Prompt.getString(in, out, "사진파일 : ");
      if (filepath.length() == 0) {
        if (photoFiles.size() > 0)
          break;
        out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
        continue;
      }
      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }
    return photoFiles;
  }
}
