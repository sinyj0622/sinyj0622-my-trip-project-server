<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<form action='login' method='post'>
  <div class="form-group">
    <label for="exampleInputEmail1">이메일</label>
    <input name='email' type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value='${cookie.email.value}'>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">비밀번호</label>
    <input name='password' type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <div class="form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label name='saveEmail' class="form-check-label" for="exampleCheck1">이메일 저장해두기</label>
  </div>
  <button type="submit" class="btn btn-outline-primary" style="float:left">로그인</button>
  <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>
 <%
    String clientId = "fNWSIg6R0T0j_Pe5Cig9";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:9999/sinyj0622-my-trip-project-server/app/auth/naverLogin", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
</form>

<script type="text/javascript">

window.fbAsyncInit = function() {
  console.log("window.fbAsyncInit() 호출됨!");
  FB.init({
    appId      : '687649938475373', // 개발자가 등록한 앱 ID
    cookie     : true,  
    xfbml      : true,  
    version    : 'v7.0' 
  });
  FB.AppEvents.logPageView();
};

(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "https://connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function checkLoginState() {
    FB.getLoginStatus(function(response) { 
        if (response.status === 'connected') { // 로그인이 정상적으로 되었을 때,
            requestAutoLogin(response.authResponse.accessToken);

        } else { // 로그인이 되지 않았을 때,
            alert("페이스북 로그인을 취소했습니다.");
        }
    });
}

function requestAutoLogin(accessToken){
	  location.href = "facebookLogin?accessToken=" + accessToken;
}

</script>


    