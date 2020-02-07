package sinyj0622.mytrip;

import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  void notifyContextInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.ContextInitialized(context);
    }
  }

  void notifyContextDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.ContextDestroyed(context);
    }
  }

  void service() {

    notifyContextInitialized();

    List<Board> boardList = (List<Board>) context.get("boardList");
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Plan> planList = (List<Plan>) context.get("planList");

    notifyContextDestroyed();

  }

  public static void main(String[] args) {
    System.out.println("서버 여행 관리 시스템입니다");

    ServerApp app = new ServerApp();
    app.service();

    /*
     * ServerSocket serverSocket = null;
     *
     * try { serverSocket = new ServerSocket(7777);
     *
     * System.out.println("클라이언트와 연결 대기중...");
     *
     * while (true) { Socket socket = serverSocket.accept(); System.out.println("클라이언트와 연결 되었음!");
     *
     * processRequest(socket);
     *
     * System.out.println("-------------------------------"); }
     *
     * } catch (IOException e) { System.out.println("서버 준비 중 오류 발생!"); e.printStackTrace(); }
     */
  }

  static void processRequest(Socket clientSocket) {


    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메세지를 수신하였음!");
      System.out.println("클라이언트:" + message);


      out.println("Hi~");
      System.out.println("클라이언트로 메세지를 전송하였음!");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }


}
