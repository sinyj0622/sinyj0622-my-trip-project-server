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
  border: 1px solid gray;
  width: 600px;
}
</style>
</head>
<body>
<nav class='navbar navbar-expand-lg navbar-light bg-light'>
<a class='navbar-brand' href='#'>여행gogo</a>
<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>
  <span class='navbar-toggler-icon'></span>
</button>
<div class='collapse navbar-collapse' id='navbarNav'>
  <ul class='navbar-nav mr-auto'>
    <li class='nav-item'>
      <a class='nav-link' href='../board/list'>게시글</span></a>
    </li>
    <li class='nav-item'>
      <a class='nav-link' href='../plan/list'>여행플랜</a>
    </li>
    <li class='nav-item'>
      <a class='nav-link' href='../member/list'>회원</a>
    </li>
  </ul>
  
<%
  Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser != null) {
%>
<span class='navbar-text'><%=loginUser.getName()%></span>
<a href='../auth/logout' class='btn btn-info btn-sm'>로그아웃</a>
<%
    } else {
%>
<a href='../auth/login' class='btn btn-info btn-sm'>로그인</a>
<%
}
%>
   
</div>
</nav>
<div class='container'>