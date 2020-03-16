package com.eomcs.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import org.apache.ibatis.io.Resources;

// 역할
// - 클래스를 찾아 객체를 생성한다.
// - 객체가 일을 하는데 필요로하는 의존 객체를 주입한다.
// - 객체의 생성과 소멸을 관리한다.
public class ApplicationContext04 {

  // 클래스 이름을 담을 저장소
  ArrayList<String> classNames = new ArrayList<>();

  public ApplicationContext04(String packageName) throws IOException, ClassNotFoundException {
    // 패키지의 실제 파일 시스템 경로를 알아낸다.
    // System.out.println("ApplicationContext : " + packageName);
    String packagePath = packageName.replace('.', '/');
    // System.out.println("ApplicationContext : " + packagePath);

    File path = Resources.getResourceAsFile(packagePath);
    // System.out.println("ApplicationContext : " + path.getAbsolutePath());

    // 해당 경로를 탐색하며 모든 클래스의 이름을 알아낸다.
    findClasses(path, packageName);

    // 클래스 이름으로 객체를 생성한다.
    createInstance();

  }

  private void findClasses(File path, String packageName) {
    // File[] files = path.listFiles(new FileFilter() {
    // @Override
    // public boolean accept(File pathname) {
    // if (pathname.isDirectory()
    // || (pathname.getName().endsWith(".class") && !pathname.getName().contains("$")))
    // return true;
    // return false;
    // }
    // });

    File[] files = path.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory() || pathname.getName().matches("^[^$]*.class$"))
          return true;
        return false;
      }
    });

    for (File f : files) {
      String classOrPackageName = packageName + "." + f.getName().replace(".class", "");
      if (f.isFile()) {
        // System.out.println("ApplicationContext : " + classOrPackageName);
        classNames.add(classOrPackageName);
      } else
        findClasses(f, classOrPackageName);
    }
  }

  private void createInstance() throws ClassNotFoundException {
    for (String className : classNames) {
      Class<?> cls = Class.forName(className);
      if (!isConcreteClass(cls)) {
        continue;
      }
      // System.out.println("ApplicationContext : " + className);

      // 클래스의 생성자 정보를 알아낸다.
      Constructor<?> constructor = cls.getConstructors()[0];

      // 생성자의 파라미터 정보를 알아낸다.
      Parameter[] params = constructor.getParameters();

      // 생성자 정보를 출력한다.
      System.out.print(cls.getName() + "(");
      for (Parameter param : params) {
        System.out.printf("[%s], ", param.getType().getSimpleName());
      }
      System.out.println(")");
    }
  }

  private boolean isConcreteClass(Class<?> cls) {
    if (cls.isInterface() || cls.isEnum() || Modifier.isAbstract(cls.getModifiers())) {
      return false;
    }
    return true;
  }
}
