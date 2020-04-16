<%@page import="sinyj0622.mytrip.domain.PhotoFile"%>
<%@page import="sinyj0622.mytrip.domain.PhotoBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="/header.jsp"></jsp:include>

<%
int planNo = (int) request.getAttribute("planNo");
PhotoBoard photoboard = (PhotoBoard) request.getAttribute("detail");
  if (photoboard == null) {
%>
<p>해당 번호의 게시물이 없습니다.</p>
<%
	} else {
%>

<h1>여행 사진</h1>
<a href='list?planNo=<%=planNo%>'>목록</a>
<br>
<form action='update' method='post' enctype='multipart/form-data'>
	플랜번호: <input name='planNo' type='text' readonly value='<%=planNo%>'><br>
	사진번호: <input name='no' type='text' readonly
		value='<%=photoboard.getNo()%>'><br> 
	내용:<br>
	<textarea name='title' rows='5' cols='60'><%=photoboard.getTitle()%></textarea>
	<br> 
	등록일: <%=photoboard.getCreatedDate()%><br> 
	조회수: <%=photoboard.getViewCount()%><br> 
	사진파일:<br>
	<p>
<%
for (PhotoFile photoFile : photoboard.getFiles()) {
%>		 
<img src='../upload/photoboard/<%=photoFile.getFilepath()%>' height='200'>
<%             
}
%>
</p>
	사진: <input name='file' type='file'><br> 
	사진: <input name='file' type='file'><br> 
	사진: <input name='file' type='file'><br> 
	사진: <input name='file' type='file'><br>
	사진: <input name='file' type='file'><br>
<p>
		<button>변경</button>
</form>
<a href='delete?no=<%=photoboard.getNo()%>&planNo=<%=planNo%>'>삭제</a>
<%
	}
%>
<jsp:include page="/footer.jsp"></jsp:include>
