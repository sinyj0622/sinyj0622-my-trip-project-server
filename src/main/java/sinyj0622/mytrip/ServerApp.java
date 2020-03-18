package sinyj0622.mytrip;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.servlet.Servlet;
import sinyj0622.sql.SqlSessionFactoryProxy;
import sinyj0622.util.ApplicationContext;

public class ServerApp {

  // 옵저버 관련 코드
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  // 스레드풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  // 서버 멈춤 여부
  boolean serverStop = false;

  // IoC 컨테이너 준비
  ApplicationContext iocContainer;

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

  @SuppressWarnings("unchecked")
  public void service() {

    notifyApplicationInitialized();

    // IocContainer
    iocContainer = (ApplicationContext) context.get("iocContainer");

    // IocContainer에서 SqlSessionFactory 객체 꺼내기
    SqlSessionFactory sqlSessionFactory =
        (SqlSessionFactory) iocContainer.getBean("sqlSessionFactory");


    try (
        // 서버쪽 연결 준비
        ServerSocket serverSocket = new ServerSocket(7777)) {


      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        executorService.submit(() -> {
          processRequest(socket);

          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
        });

        // 서버가 true(멈춤)
        if (serverStop) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    // 스레드풀 종료
    executorService.shutdown();


    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    notifyApplicationDestroyed();

    // 스레드풀 종료
    executorService.shutdown();

  } // service()

  @SuppressWarnings("unchecked")
  void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=>%s\n", request);

      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      Servlet servlet = (Servlet) iocContainer.getBean(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());

          // 서버쪽 화면
          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }
      out.println("!end!");
      out.flush();
      System.out.println("클라이언트에게 응답하였음!");
      System.out.println("-------------------------------");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
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


  public static void main(String[] args) {
    System.out.println("서버 여행 관리 시스템입니다");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();

  }
}
