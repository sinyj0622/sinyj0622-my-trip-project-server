package sinyj0622.mytrip.web;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  static Logger logger = LogManager.getLogger(AuthController.class);

  @Autowired
  MemberService memberService;
  
  @GetMapping("signUp")
  public void signUp() {}

  @GetMapping("form")
  public void form() {}

  @PostMapping("login")
  public String login( //
      String email, //
      String password, //
      String saveEmail, //
      HttpServletResponse response, //
      HttpSession session, //
      Model model) throws Exception {

    Cookie cookie = new Cookie("email", email);
    if (saveEmail != null) {
      cookie.setMaxAge(60 * 60 * 24 * 30);
    } else {
      cookie.setMaxAge(0);
    }
    response.addCookie(cookie);

    Member member = memberService.get(email, password);
    if (member != null) {
      session.setAttribute("loginUser", member);
      model.addAttribute("refreshUrl", "2;url=../../index.html");
    } else {
      session.invalidate();
      model.addAttribute("refreshUrl", "2;url=form");
    }

    return "auth/login";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:../../index.html";
  }

  
  @GetMapping("naverLogin")
  public void naverLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws Exception {
	  String clientId = "fNWSIg6R0T0j_Pe5Cig9";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "1EqUNUsCNH";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:9999/sinyj0622-my-trip-project-server/app/auth/naverLogin", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    logger.info("apiURL="+apiURL);
	    
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      logger.info("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    	  logger.info(res.toString());
	    	  
	    	// json인 access_token을 String 형식으로 받아오기.
	          JsonParser parsing = new JsonParser();
	          JsonElement jsonElement = parsing.parse(res.toString());

	          // Object obj = parsing.parse(res.toString());
	          // JsonObject jsonObj = (JsonObject)obj;
	          // access_token = (String)jsonObj.get("access_token");
	          // refresh_token = (String)jsonObj.get("refresh_token");

	          access_token = jsonElement.getAsJsonObject().get("access_token").toString();
	          refresh_token = jsonElement.getAsJsonObject().get("refresh_token").toString();
	      
	      
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
  }


  @SuppressWarnings("unchecked")
  @GetMapping("facebookLogin")
  public String facebookLogin( //
      String accessToken, //
      HttpSession session, //
      Model model) throws Exception {

    // 엑세스 토큰을 가지고 페이스북 서버에 사용자 정보를 요청한다.
    // 1) Facebook Graph API 실행하기
    // => JSON 또는 XML을 리턴하는 HTTP 요청 하는 방법?
    // 스프링에서 제공하는 RestTemplate 클래스를 사용하라.
    // - JSON 또는 XML을 자바 객체로 자동 변환해준다
    RestTemplate restTemplate = new RestTemplate();

    // 2) 서버에 요청하기
    // 첫번째 파라미터 : 요청 url
    // 두번쨰 파라미터 : 서버가 응답한 json을 어떤 타입의 객체로 만들지 지정

    Map<String, Object> response =
        restTemplate.getForObject("https://graph.facebook.com/v7.0/me?access_token={1}&fields={2}",
            Map.class, accessToken, "id,name,email");

    // 3) 사용자 정보 확인
    String email = (String) response.get("email");
    logger.info("페이스북 로그인 사용자 이메일: {}", email);
    logger.info("페이스북 로그인 사용자 id: {}", response.get("id"));
    logger.info("페이스북 로그인 사용자 name: {}", response.get("name"));

    if (email == null) {
      logger.info("페이스북 엑세스토큰이 무효하다.");
      // 엑세스 토큰이 무효하다면, 다시 로그인 입력폼으로 보낸다.
      session.invalidate();
      model.addAttribute("refreshUrl", "2;url=form");
      return "auth/login";
    }

    // 페이스북에 정상적으로 로그인 되었다면
    // 현재 서버에 등록된 사용자를 이메일로 찾는다.
    Member member = memberService.get(email);
    if (member == null) {
      member = new Member();
      member.setName("이름없음");
      member.setEmail(email);
      member.setPassWord(UUID.randomUUID().toString());
      member.setNickname("");
      memberService.add(member);
      logger.info("페이스북 사용자를 회원으로 등록한다.");
    }

    session.setAttribute("loginUser", member);
    model.addAttribute("refreshUrl", "2;url=../../index.html");

    return "auth/login";
  }
}
