<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="/header.jsp"></jsp:include>
<%
Board board = (Board) request.getAttribute("update");

if (board == null){
%>
<p>해당 게시글을 찾을 수 없습니다</p>

<%
} else {
%>

<h1>게시물 변경</h1>
<form action='update' method='post'>
번호: <input name='no' readonly type='text' value='<%=board.getNo()%>'><br>
내용:<br>
<textarea name='text' rows='5' cols='60'><%=board.getText()%></textarea><br>
등록일: <%=board.getDate()%><br>
조회수: <%=board.getViewCount()%><br>
<button>변경</button>
</form>

<%
}
%>

<jsp:include page="/footer.jsp"></jsp:include>