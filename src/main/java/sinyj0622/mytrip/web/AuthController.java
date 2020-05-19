package sinyj0622.mytrip.web;


import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
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
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  static Logger logger = LogManager.getLogger(AuthController.class);

  @Autowired
  MemberService memberService;

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
