<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="/header.jsp"></jsp:include>

  <h1>게시글</h1>
  <a href='add'>새 글</a><br>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
<% 
  List<Board> boards = (List<Board>) request.getAttribute("list");

for (Board board : boards){
%> 
  <tr>
  <td><%=board.getNo()%></td> 
  <td><a href='detail?no=<%=board.getNo()%>'><%=board.getText()%></a></td> 
  <td><%=board.getDate()%></td> 
  <td><%=board.getViewCount()%></td>
  </tr>
<% 
}
%>  
</table>

<jsp:include page="/footer.jsp"></jsp:include>