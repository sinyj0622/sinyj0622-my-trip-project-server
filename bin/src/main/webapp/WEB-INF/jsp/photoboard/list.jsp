<%@page import="sinyj0622.mytrip.domain.PhotoBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<jsp:include page="../header.jsp"></jsp:include>    

<%
int planNo = (int) request.getAttribute("planNo");
if (planNo == 0){
%>    
<p>해당 번호의 게시물이 없습니다.</p>
<%} else { %>   
  <h1>여행 사진 목록</h1>
  <a href='add?planNo=<%=planNo%>'>새 사진</a><br>  <table border='1'>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
<%
List<PhotoBoard> photoboards = (List<PhotoBoard>) request.getAttribute("list");
for (PhotoBoard p : photoboards){
%>
  <tr><td><%=p.getNo()%></td> 
  <td><a href='detail?no=<%=p.getNo()%>&planNo=<%=planNo%>'><%=p.getTitle()%></a></td> 
  <td><%=p.getCreatedDate()%></td> 
  <td><%=p.getViewCount()%></td> </tr>
<%
}
%>
<%
}
%>
</table>

<jsp:include page="../footer.jsp"></jsp:include>  