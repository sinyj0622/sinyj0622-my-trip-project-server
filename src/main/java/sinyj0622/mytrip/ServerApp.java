package sinyj0622.mytrip;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {


  public static void main(String[] args) {
    System.out.println("서버 여행 관리 시스템입니다");


    ServerSocket serverSocket = null;

    try {
      serverSocket = new ServerSocket(7777);

      System.out.println("클라이언트와 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결 되었음!");

        processRequest(socket);

        System.out.println("-------------------------------");
      }

    } catch (IOException e) {
      System.out.println("서버 준비 중 오류 발생!");
      e.printStackTrace();
    }
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
