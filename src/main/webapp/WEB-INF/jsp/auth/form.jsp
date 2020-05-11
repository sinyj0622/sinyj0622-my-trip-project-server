<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="../header.jsp"/>

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
  <button type="submit" class="btn btn-outline-primary">로그인</button>
</form>


<jsp:include page="../footer.jsp"/>
    