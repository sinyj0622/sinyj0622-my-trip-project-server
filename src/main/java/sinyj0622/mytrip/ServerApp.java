package sinyj0622.mytrip;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import sinyj0622.mytrip.context.ApplicationContextListener;
import sinyj0622.util.RequestHandler;
import sinyj0622.util.RequestMappingHandlerMapping;

public class ServerApp {

	// log4j
	static Logger logger = LogManager.getLogger(ServerApp.class);

	// 옵저버 관련 코드
	Set<ApplicationContextListener> listeners = new HashSet<>();
	Map<String, Object> context = new HashMap<>();

	// 스레드풀
	ExecutorService executorService = Executors.newCachedThreadPool();

	// 서버 멈춤 여부
	boolean serverStop = false;

	// IoC 컨테이너 준비
	ApplicationContext iocContainer;

	RequestMappingHandlerMapping handlerMapper;

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

		handlerMapper = (RequestMappingHandlerMapping) context.get("handlerMapper");

		// IocContainer
		iocContainer = (ApplicationContext) context.get("iocContainer");


		try (
				// 서버쪽 연결 준비
				ServerSocket serverSocket = new ServerSocket(7777)) {


			while (true) {
				Socket socket = serverSocket.accept();
				logger.info("클라이언트와 연결되었음!");

				executorService.submit(() -> {
					processRequest(socket);
				});

				// 서버가 true(멈춤)
				if (serverStop) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error(String.format("서버 준비 중 오류 발생!: %s", e.getMessage()));
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

			String[] requestLine = in.nextLine().split(" ");

			while(true) {
				String line = in.nextLine();
				if (line.length() == 0) { // 없을때까지 읽는다
					break;
				}
			}
			String method = requestLine[0];
			String requestUri = requestLine[1];
			logger.info(String.format("method=> %s", method));
			logger.info(String.format("request-uri=> %s", requestUri));

			String servletPath = getServletPath(requestUri);
			logger.info(String.format("servlet-path=> %s", servletPath));

			Map<String,String> params = getParameters(requestUri);

			// HTTP 응답 헤더 출력
			printResponseHeader(out);

			if (servletPath.equalsIgnoreCase("/server/stop")) {
				quit(out);
				return;
			}

			RequestHandler requestHandler = handlerMapper.getHandler(servletPath);

			if (requestHandler != null) {
				try {
					requestHandler.getMethod().invoke(//
							requestHandler.getBean(), params, out);


				} catch (Exception e) {
					out.println("요청 처리 중 오류 발생!");
					out.println(e.getMessage());

					// 서버쪽 화면
					logger.info("클라이언트 요청 처리 중 오류 발생");
					StringWriter strWriter = new StringWriter();
					e.printStackTrace(new PrintWriter(strWriter));
					logger.debug(strWriter.toString());
				}
			} else {
				notFound(out);
		        logger.info("해당 명령을 지원하지 않습니다.");
			}
			out.flush();
			logger.info("클라이언트에게 응답하였음!");
			logger.info("-------------------------------");

		} catch (Exception e) {
			System.out.println("예외 발생:");
			StringWriter strWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(strWriter));
			logger.debug(strWriter.toString());
		}
	}

	private Map<String, String> getParameters(String requestUri) throws Exception {
		Map<String, String> params = new HashMap<>();
	    String[] items = requestUri.split("\\?");
	    if (items.length > 1) {
	      logger.debug(String.format("query string => %s", items[1]));
	      String[] entries = items[1].split("&");
	      for (String entry : entries) {
	        logger.debug(String.format("parameter => %s", entry));
	        String[] kv = entry.split("=");

	        if (kv.length > 1) {
	          // 웹브라우저가 URL 인코딩하여 보낸 데이터를
	          // 디코딩하여 String 객체로 만든다.
	          String value = URLDecoder.decode(kv[1], "UTF-8");

	          params.put(kv[0], value);
	        } else {
	          params.put(kv[0], "");
	        }
	      }
	    }
	    return params;
	}

	private String getServletPath(String requestUri) {
		return requestUri.split("\\?")[0];
	}

	private void notFound(PrintStream out) throws IOException {
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<meta charset='UTF-8'>");
		    out.println("<title>실행 오류!</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h1>실행 오류!</h1>");
		    out.println("<p>요청한 명령을 처리할 수 없습니다.</p>");
		    out.println("</body>");
		    out.println("</html>");
	}

	private void quit(PrintStream out) throws IOException {
		serverStop = true;
		out.println("OK");
		out.println("!end!");
		out.flush();
	}


	private void printResponseHeader(PrintStream out) {
		out.println("HTTP/1.1 200 OK");
		out.println("Server: bitcampServer");
		out.println();
	}

	public static void main(String[] args) {
		logger.info("서버 여행 관리 시스템입니다");

		ServerApp app = new ServerApp();
		app.addApplicationContextListener(new ContextLoaderListener());
		app.service();

	}
}
