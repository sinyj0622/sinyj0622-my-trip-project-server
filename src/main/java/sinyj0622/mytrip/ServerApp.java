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
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.PhotoFileDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.service.BoardService;
import sinyj0622.mytrip.service.MemberService;
import sinyj0622.mytrip.service.PhotoBoardService;
import sinyj0622.mytrip.service.PlanService;
import sinyj0622.mytrip.servlet.BoardAddServlet;
import sinyj0622.mytrip.servlet.BoardDeleteServlet;
import sinyj0622.mytrip.servlet.BoardDetailServlet;
import sinyj0622.mytrip.servlet.BoardListServlet;
import sinyj0622.mytrip.servlet.BoardUpdateServlet;
import sinyj0622.mytrip.servlet.LoginServlet;
import sinyj0622.mytrip.servlet.MemberAddServlet;
import sinyj0622.mytrip.servlet.MemberDeleteServlet;
import sinyj0622.mytrip.servlet.MemberDetailServlet;
import sinyj0622.mytrip.servlet.MemberListServlet;
import sinyj0622.mytrip.servlet.MemberSearchServlet;
import sinyj0622.mytrip.servlet.MemberUpdateServlet;
import sinyj0622.mytrip.servlet.PhotoBoardAddServlet;
import sinyj0622.mytrip.servlet.PhotoBoardDeleteServlet;
import sinyj0622.mytrip.servlet.PhotoBoardDetailServlet;
import sinyj0622.mytrip.servlet.PhotoBoardListServlet;
import sinyj0622.mytrip.servlet.PhotoBoardUpdateServlet;
import sinyj0622.mytrip.servlet.PlanAddServlet;
import sinyj0622.mytrip.servlet.PlanDeleteServlet;
import sinyj0622.mytrip.servlet.PlanDetailServlet;
import sinyj0622.mytrip.servlet.PlanListServlet;
import sinyj0622.mytrip.servlet.PlanSearchServlet;
import sinyj0622.mytrip.servlet.PlanUpdateServlet;
import sinyj0622.mytrip.servlet.Servlet;
import sinyj0622.sql.PlatformTransactionManager;
import sinyj0622.sql.SqlSessionFactoryProxy;

public class ServerApp {

  // 옵저버 관련 코드
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servlets = new HashMap<>(); // 서블릿객체저장

  // 스레드풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  // 서버 멈춤 여부
  boolean serverStop = false;

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

    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.get("sqlSessionFactory");
    PlatformTransactionManager txManager = (PlatformTransactionManager) context.get("txManager");

    BoardService boardService = (BoardService) context.get("boardService");
    MemberService memberService = (MemberService) context.get("memberService");
    PlanService planService = (PlanService) context.get("planService");
    PhotoBoardService photoBoardService = (PhotoBoardService) context.get("photoBoardService");
    PhotoFileDao photoFileDao = (PhotoFileDao) context.get("photoFileDao");

    servlets.put("/board/list", new BoardListServlet(boardService));
    servlets.put("/board/add", new BoardAddServlet(boardService));
    servlets.put("/board/detail", new BoardDetailServlet(boardService));
    servlets.put("/board/delete", new BoardDeleteServlet(boardService));
    servlets.put("/board/update", new BoardUpdateServlet(boardService));

    servlets.put("/member/list", new MemberListServlet(memberService));
    servlets.put("/member/add", new MemberAddServlet(memberService));
    servlets.put("/member/detail", new MemberDetailServlet(memberService));
    servlets.put("/member/delete", new MemberDeleteServlet(memberService));
    servlets.put("/member/update", new MemberUpdateServlet(memberService));
    servlets.put("/member/search", new MemberSearchServlet(memberService));
    servlets.put("/member/login", new LoginServlet(memberService));

    servlets.put("/plan/list", new PlanListServlet(planService));
    servlets.put("/plan/add", new PlanAddServlet(planService));
    servlets.put("/plan/detail", new PlanDetailServlet(planService));
    servlets.put("/plan/delete", new PlanDeleteServlet(planService));
    servlets.put("/plan/update", new PlanUpdateServlet(planService));
    servlets.put("/plan/search", new PlanSearchServlet(planService));

    servlets.put("/photoBoard/list", new PhotoBoardListServlet(planService,photoBoardService));
    servlets.put("/photoBoard/add",
        new PhotoBoardAddServlet(planService,photoBoardService));
    servlets.put("/photoBoard/detail", new PhotoBoardDetailServlet(photoBoardService));
    servlets.put("/photoBoard/delete",
        new PhotoBoardDeleteServlet(photoBoardService));
    servlets.put("/photoBoard/update",
        new PhotoBoardUpdateServlet(photoBoardService));

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

      Servlet servlet = servlets.get(request);

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
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }
}
