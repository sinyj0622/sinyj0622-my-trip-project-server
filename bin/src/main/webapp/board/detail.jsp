<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="/header.jsp"></jsp:include>

<%
Board board = (Board) request.getAttribute("detail");
%>

<h1>게시물 상세정보</h1>
번호: <%=board.getNo()%><br>
내용: <%=board.getText()%><br>
등록일: <%=board.getDate()%><br>
조회수: <%=board.getViewCount()%><br>
<p><a href='delete?no=<%=board.getNo()%>'>삭제</a>
<a href='update?no=<%=board.getNo()%>'>변경</a></p>


<jsp:include page="/footer.jsp"></jsp:include>