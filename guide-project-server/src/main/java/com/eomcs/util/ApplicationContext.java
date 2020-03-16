package com.eomcs.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;

// 역할
// - 클래스를 찾아 객체를 생성한다.
// - 객체가 일을 하는데 필요로하는 의존 객체를 주입한다.
// - 객체의 생성과 소멸을 관리한다.
public class ApplicationContext {

  // 클래스 이름을 담을 저장소
  ArrayList<String> classNames = new ArrayList<>();

  // @Component 애노테이션이 붙은 class를 보관할 저장소
  ArrayList<Class<?>> componentClasses = new ArrayList<>();

  // 객체를 보관할 저장소
  HashMap<String, Object> objPool = new HashMap<>();

  public ApplicationContext(String packageName, Map<String, Object> beans)
      throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {

    // Map에 들어있는 객체를 먼저 객체풀에 보관한다.
    Set<String> keySet = beans.keySet();
    for (String key : keySet) {
      objPool.put(key, beans.get(key));
    }

    // 그런 후 패키지의 클래스를 찾아 인스턴스를 만들고 보관한다.
    // 패키지의 실제 파일 시스템 경로를 알아낸다.
    File path = Resources.getResourceAsFile(packageName.replace('.', '/'));

    // 해당 경로를 탐색하며 모든 클래스의 이름을 알아낸다.
    findClasses(path, packageName);

    // 클래스 이름으로 클래스를 로딩한다.
    // @Component 애노테이션이 붙은 클래스를 별도의 목록으로 준비한다.
    prepareComponentClasses();

    // concrete 클래스의 객체를 생성한다.
    // concrete class의 생성자를 호출할 때 의존 객체를 함께 주입한다.
    // 의존객체는 객체풀에서 찾아 주입한다.
    // 의존객체가 없으면 생성하여 주입한다.
    for (Class<?> cls : componentClasses) {
      try {
        // 일반 클래스라면 객체를 생성한다.
        createInstance(cls);

      } catch (Exception e) {
        System.out.printf("%s 클래스의 객체를 생성할 수 없습니다.\n", cls.getName());
      }
    }
  }

  public void printBeans() {
    System.out.println("--------------------------------------------------");
    Set<String> beanNames = objPool.keySet();
    for (String beanName : beanNames) {
      System.out.printf("%s =====> %s\n", //
          beanName, // 객체 이름
          objPool.get(beanName).getClass().getName() // 클래스명
      );
    }
  }

  // 객체를 한 개 등록
  public void addBean(String name, Object bean) {
    objPool.put(name, bean);
  }

  public Object getBean(String name) {
    return objPool.get(name);
  }

  private void prepareComponentClasses() throws ClassNotFoundException {
    for (String className : classNames) {
      Class<?> cls = Class.forName(className);
      if (!isComponentClass(cls)) {
        continue;
      }
      componentClasses.add(cls);
    }
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

  private Object createInstance(Class<?> cls) throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // System.out.println("ApplicationContext : " + className);

    // 클래스의 생성자 정보를 알아낸다.
    Constructor<?> constructor = cls.getConstructors()[0];

    // 생성자의 파라미터 정보를 알아낸다.
    Parameter[] params = constructor.getParameters();

    // 생성자에 넘겨줄 파라미터 객체를 준비한다.
    ArrayList<Object> values = new ArrayList<>();
    for (Parameter param : params) {
      values.add(getParameterInstance(param));
    }

    // 생성자를 호출하여 객첼르 준비한다.
    Object obj = constructor.newInstance(values.toArray());
    // System.out.printf("%s 객체를 생성하였음!\n", cls.getSimpleName());

    // 생성된 객체는 객체풀에 보관한다.
    objPool.put(getBeanName(cls), obj);
    return obj;

  }

  private String getBeanName(Class<?> cls) {
    Component compAnno = cls.getAnnotation(Component.class);

    // @Component 애노테이션이 없거나 이름을 지정하지 않았으면
    // 클래스 이름을 빈의 이름으로 사용한다.
    if (compAnno == null || compAnno.value().length() == 0)
      return cls.getName();
    return compAnno.value();
  }

  private Object getParameterInstance(Parameter param)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    // 먼저 객체를 보관하는 자료구조에 파라미터 객체가 있는지 검사하고 있다면 리턴한다.
    // 존재하지 않는다면 파라미터 객체를 생성한다.
    Collection<?> objs = objPool.values();
    for (Object obj : objs) {
      // 있으면, 같은 객체를 또 생성하지 않고 기존에 생성된 객체를 리턴한다.
      if (param.getType().isInstance(obj)) {
        return obj;
      }
    }
    // 없으면 파라미터 객체를 생성한다.
    // 단, 현재 클래스 이름으로 등록된 객체에 대해서만 파라미터 객체를 생성할 수 있다.
    Class<?> cls = findParameterClassInfo(param.getType());
    if (cls == null) {
      // 파라미터에 해당하는 적절한 클래스를 찾지 못한 경우
      // 파라미터 객체를 생성할 수 없다.
      return null;
    }
    return createInstance(cls);
  }

  private Class<?> findParameterClassInfo(Class<?> paramType) {
    // concrete class 목록에서 파라미터에 해당하는 클래스가 있는지 검사한다.
    for (Class<?> cls : componentClasses) {
      if (paramType.isInterface()) {

        // 파라미터가 인터페이스라면
        // 각각의 클래스에 대해 그 인터페이스를 구현했는지 검사한다.
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> interfaceInfo : interfaces) {
          if (interfaceInfo == paramType) {
            return cls;
          }
        }
      } else if (isType(cls, paramType)) {
        // 파라미터가 클래스라면
        // 각각의 클래스에 대해 같은 타입이거나 super class인지 검사한다.
        return cls;
      }
    }

    // 파라미터에 해당하는 타입이 concrete 클래스 목록에 없다면, null 리턴
    return null;
  }

  private boolean isType(Class<?> cls, Class<?> target) {
    // super class로 따라 올라가면서 같은 타입인지 검사
    if (cls == target) {
      return true;
    }
    if (cls == Object.class) {
      // 더 이상 상위 클래스가 없다면, false
      return false;
    }
    return isType(cls.getSuperclass(), target);
  }

  private boolean isComponentClass(Class<?> cls) {
    if (cls.isInterface() || cls.isEnum() || Modifier.isAbstract(cls.getModifiers())) {
      return false;
    }

    // 클래스에서 @Component 애노테이션 정보를 추출한다.
    Component compAnno = cls.getAnnotation(Component.class);
    if (compAnno == null)
      return false;

    return true;
  }
}
