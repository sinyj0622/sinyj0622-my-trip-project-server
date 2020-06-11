<%@page import="java.util.List"%>
<%@page import="sinyj0622.mytrip.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../header.jsp"></jsp:include>    
    
<%
List<Member> members = (List<Member>) request.getAttribute("search");
for (Member member : members){
%>   
   <h1>회원 검색 결과</h1>
   <p><a href='/member/list'>목록</a></p>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>이메일</th>
    <th>전화</th>
    <th>등록일</th>
  </tr>
  <tr><td><%=member.getNo()%></td> <td><a href='detail?no=<%=member.getNo()%>'><%=member.getName()%></a></td>
  <td><%=member.getEmail()%></td> <td><%=member.getPhonenumber()%></td><td><%=member.getRegisteredDate()%></td></tr>
<%
}
%>
</table>
<jsp:include page="../footer.jsp"></jsp:include>  