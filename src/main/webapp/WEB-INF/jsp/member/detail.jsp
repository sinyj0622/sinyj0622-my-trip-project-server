<%@page import="java.util.List"%>
<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<jsp:include page="../header.jsp"></jsp:include>    

<%
Member member = (Member) request.getAttribute("detail");
%>    

 <h1>회원 상세정보</h1>
<form action='update' method='post' enctype='multipart/form-data'>
<img src='../upload/member/사진.jpg' height='300'><br>
번호: <input name='no' type='text' readonly value='<%=member.getNo()%>'><br>
이름: <input name='name' type='text' value='<%=member.getName()%>'><br>
이메일: <input name='email' type='email' value='<%=member.getEmail()%>'><br>
암호: <input name='password' type='password'><br>
사진: <input name='myphoto' type='file'><br>
전화: <input name='tel' type='tel' value='<%=member.getPhonenumber()%>'><br>
<p><button>변경</button>
<a href='delete?no=<%=member.getNo()%>'>삭제</a></p>
</form>

<jsp:include page="../footer.jsp"></jsp:include>  