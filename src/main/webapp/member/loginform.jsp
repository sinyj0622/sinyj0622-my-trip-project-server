<%@page import="java.util.List"%>
<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp"></jsp:include>    

<%
String email = (String) request.getAttribute("email");
%>

<h1>로그인</h1>
<form action='login' method='post'>
이메일: <input name='email' type='email' value='<%=email%>'><input name='userEmail' type='checkbox'>이메일 기억하기<br>
암호: <input name='password' type='password'><br>
<button>로그인</button>
</form>


<jsp:include page="/footer.jsp"></jsp:include>  