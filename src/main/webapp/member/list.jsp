<%@page import="java.util.List"%>
<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<jsp:include page="/header.jsp"></jsp:include>    
    

    
  <h1>회원</h1>
  <a href='add'>새 회원</a><br>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>이메일</th>
    <th>전화</th>
    <th>등록일</th>
  </tr>
<%
List<Member> members = (List<Member>) request.getAttribute("list");
for (Member member : members){
%>    
  <tr><td><%=member.getNo()%></td> <td><a href='detail?no=<%=member.getNo()%>'><%=member.getName()%></a></td>
  <td><%=member.getEmail()%></td> <td><%=member.getPhonenumber()%></td><td><%=member.getRegisteredDate()%></td></tr>
<%
}
%>
</table>
<hr>
<form action='search'>
검색어: <input name='keyword' type='text'>
<button>검색</button>    

<jsp:include page="/footer.jsp"></jsp:include>  