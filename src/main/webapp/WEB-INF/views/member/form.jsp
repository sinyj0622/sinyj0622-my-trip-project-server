<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<h1>회원 입력</h1>
<form action='add' method='post' enctype='multipart/form-data'>
이름: <input name='name' type='text'><br>
별명: <input name='nickname' type='text'><br>
이메일: <input name='email' type='text'><br>
암호: <input name='passWord' type='password'><br>
사진: <input name='myphoto' type='file'><br>
전화: <input name='phonenumber' type='text'><br>
<button>제출</button>
</form>

