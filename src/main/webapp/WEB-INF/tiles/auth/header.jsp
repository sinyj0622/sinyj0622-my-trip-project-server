<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul class="nav justify-content-center" style="padding-top:10px;">
  <li class="nav-item">
    <a class="nav-link active" href="../board/list">게시글</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="../plan/list">여행플랜</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="../member/list">회원</a>
  </li>
<%
  Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser != null) {
%>
  <li class="nav-item">
<span class='navbar-text'><%=loginUser.getName()%></span>
<a href='../auth/logout' class='nav-link'>로그아웃</a>
  </li>
<%
    } else {
%>
  <li class="nav-item">
<a href='../auth/form' class='nav-link'>로그인</a>
  </li>
<%
}
%>
   
</ul>
