<%@page import="sinyj0622.mytrip.domain.Plan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../header.jsp"></jsp:include>

<%
List<Plan> plans = (List<Plan>) request.getAttribute("search");
if (plans == null){
%>

<p>검색 결과가 없습니다</p>
<% } else {
for (Plan plan : plans){
%>
<h1>플랜 검색 결과</h1>
<a href='list'>목록</a>
<br>
<table border='1'>
	<tr>
		<th>번호</th>
		<th>여행 주제</th>
		<th>여행지</th>
		<th>시작일</th>
		<th>종료일</th>
	</tr>
	<tr>
		<td><%=plan.getNo()%></td>
		<td><%=plan.getTravelTitle()%></td>
		<td><%=plan.getDestnation()%></td>
		<td><%=plan.getStartDate()%></td>
		<td><%=plan.getEndDate()%></td>
	</tr>
<%
}
%>
<%
}
%>
</table>
<jsp:include page="../footer.jsp"></jsp:include>
