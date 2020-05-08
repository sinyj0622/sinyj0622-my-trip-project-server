<%@page import="sinyj0622.mytrip.domain.Plan"%>
<%@page import="java.util.List"%>
<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<jsp:include page="../header.jsp"></jsp:include>    

  <h1>여행 플랜 목록</h1>
<form action='search'>
여행 주제 <input name='title' type='text'><br>
여행지 <input name='spot' type='text'>

<button>검색</button><br>
  <a href='add'>새 글</a><br>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>여행 주제</th>
    <th>여행지</th>
    <th>시작일</th>
    <th>종료일</th>
  </tr>
<%
List<Plan> plans = (List<Plan>) request.getAttribute("list");
for (Plan plan : plans){
%>    
  <tr><td><%=plan.getNo()%></td> <td><a href='detail?no=<%=plan.getNo()%>'><%=plan.getTravelTitle()%></a></td> 
  <td><%=plan.getDestnation()%></td> <td><%=plan.getStartDate()%></td> <td><%=plan.getEndDate()%></td></tr>
<%
}
%>
</table>
    

<jsp:include page="../footer.jsp"></jsp:include>  