package sinyj0622.mytrip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.mytrip.servlet.BoardAddServlet;
import sinyj0622.mytrip.servlet.BoardDeleteServlet;
import sinyj0622.mytrip.servlet.BoardDetailServlet;
import sinyj0622.mytrip.servlet.BoardListServlet;
import sinyj0622.mytrip.servlet.BoardUpdateServlet;
import sinyj0622.mytrip.servlet.MemberAddServlet;
import sinyj0622.mytrip.servlet.MemberDeleteServlet;
import sinyj0622.mytrip.servlet.MemberDetailServlet;
import sinyj0622.mytrip.servlet.MemberListServlet;
import sinyj0622.mytrip.servlet.MemberUpdateServlet;
import sinyj0622.mytrip.servlet.PlanAddServlet;
import sinyj0622.mytrip.servlet.PlanDeleteServlet;
import sinyj0622.mytrip.servlet.PlanDetailServlet;
import sinyj0622.mytrip.servlet.PlanListServlet;
import sinyj0622.mytrip.servlet.PlanUpdateServlet;
import sinyj0622.mytrip.servlet.Servlet;

public class ServerApp {

	// 옵저버 관련 코드
	Set<ApplicationContextListener> listeners = new HashSet<>();
	Map<String, Object> context = new HashMap<>();

	Map<String, Servlet> servlets = new HashMap(); // 서블릿객체저장

	List<Board> boards;
	List<Plan> plans;
	List<Member> members;

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

		boards = (List<Board>) context.get("boardList");
		plans = (List<Plan>) context.get("planList");
		members = (List<Member>) context.get("memberList");

		servlets.put("/board/list", new BoardListServlet(boards));
		servlets.put("/board/add", new BoardAddServlet(boards));
		servlets.put("/board/detail", new BoardDetailServlet(boards));
		servlets.put("/board/delete", new BoardDeleteServlet(boards));
		servlets.put("/board/update", new BoardUpdateServlet(boards));
		
		servlets.put("/member/list", new MemberListServlet(members));
		servlets.put("/member/add", new MemberAddServlet(members));
		servlets.put("/member/detail", new MemberDetailServlet(members));
		servlets.put("/member/delete", new MemberDeleteServlet(members));
		servlets.put("/member/update", new MemberUpdateServlet(members));
		
		servlets.put("/plan/list", new PlanListServlet(plans));
		servlets.put("/plan/add", new PlanAddServlet(plans));
		servlets.put("/plan/detail", new PlanDetailServlet(plans));
		servlets.put("/plan/delete", new PlanDeleteServlet(plans));
		servlets.put("/plan/update", new PlanUpdateServlet(plans));

		try (
				// 서버쪽 연결 준비
				ServerSocket serverSocket = new ServerSocket(7777)) {

			System.out.println("클라이언트 연결 대기중...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트와 연결되었음!");

				if (processRequest(socket) == 9) {
					break;
				}

				System.out.println("--------------------------------------");
			}

		} catch (Exception e) {
			System.out.println("서버 준비 중 오류 발생!");
		}

		notifyApplicationDestroyed();

	} // service()

	@SuppressWarnings("unchecked")
	int processRequest(Socket clientSocket) {
		try (Socket socket = clientSocket;
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

			System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

			while (true) {
				String request = in.readUTF();
				System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

				switch (request) {
				case "quit":
					quit(out);
					return 0;
				case "/server/stop":
					quit(out);
					return 9;
				}

				Servlet servlet = servlets.get(request);

				if (servlet != null) {
					try {
						servlet.service(in, out);

					} catch (Exception e) {
						out.writeUTF("FAIL");
						out.writeUTF(e.getMessage());

						System.out.println("클라이언트 요청 처리 중 오류 발생:");
						e.printStackTrace();
					}
				} else {
					notFound(out);
				}

				out.flush();
				System.out.println("클라이언트로 메시지를 전송하였음!");
			}
		} catch (Exception e) {
			System.out.println("예외 발생:");
			e.printStackTrace();
			return -1;
		}
	}

	private void notFound(ObjectOutputStream out) throws IOException {
		out.writeUTF("OK");
		out.writeUTF("요청한 명령을 처리할 수 없습니다.");
	}

	private void quit(ObjectOutputStream out) throws IOException {
		out.writeUTF("OK");
		out.flush();
	}

	private void updatePlan(ObjectInputStream in, ObjectOutputStream out) throws IOException {
		
	}



	public static void main(String[] args) {
		System.out.println("서버 여행 관리 시스템입니다");

		ServerApp app = new ServerApp();
		app.addApplicationContextListener(new DataLoaderListener());
		app.service();

	}
}
