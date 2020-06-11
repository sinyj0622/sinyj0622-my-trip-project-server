<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="../header.jsp"></jsp:include>

<form action='update' method='post'>
번호: <input name='no' readonly type='text' value='${board.no}'><br>
내용:<br>
<textarea name='title' rows='5' cols='60'>${board.title}</textarea><br>
등록일: ${board.date}<br>
조회수: ${board.viewCount}<br>
<button>변경</button>
</form>

<jsp:include page="../footer.jsp"></jsp:include>