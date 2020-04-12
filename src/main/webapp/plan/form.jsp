<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="/header.jsp"></jsp:include>

<h1>여행 플랜 작성</h1>
<form action='add' method='post'>
여행 주제: <input name='title'  type='text' ><br>
여행지: <input name='place'  type='text' ><br>
여행인원: <input name='person'  type='number' >명<br>
시작일: <input name='sdt'  type='date' ><br>
종료일: <input name='edt'  type='date' ><br>
예상 경비: <input name='money'  type='number'>만원<br>
<button>등록</button>
</form>

<jsp:include page="/footer.jsp"></jsp:include>