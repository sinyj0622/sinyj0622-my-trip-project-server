<%@page import="sinyj0622.mytrip.domain.Plan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="/header.jsp"></jsp:include>

<%
Plan plan = (Plan) request.getAttribute("plan");
%>    

<h1>여행사진 등록</h1>
<form action='add' method='post' enctype='multipart/form-data'>
플랜번호: <input name='planNo'  type='text' value='<%=plan.getNo()%>' readonly><br>
제목: <input name='title'  type='text' ><br>
<input name='file'  type='file' ><br>
<input name='file'  type='file' ><br>
<input name='file'  type='file' ><br>
<input name='file'  type='file' ><br>
<input name='file'  type='file' ><br>
<button>등록</button>
</form>

<jsp:include page="/footer.jsp"></jsp:include>