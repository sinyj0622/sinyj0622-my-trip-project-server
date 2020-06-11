<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>여행gogo</title>
<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>
<style>
body {
  background-color: light;
}
div.container {
  background: white;
  width: 600px;
}
</style>
</head>
<body>
<ul class="nav justify-content-center">
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
<div class='container'>