<%@page import="sinyj0622.mytrip.domain.Plan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<jsp:include page="../header.jsp"></jsp:include>    

<%
Plan plan = (Plan) request.getAttribute("detail");
if (plan == null){
%>    
<p>해당 번호의 게시물이 없습니다.</p>
<%} else { %>
<h1>여행 플랜 목록</h1>
<a href='list'>목록</a><br>
번호: <%=plan.getNo()%><br>
여행 주제: <%=plan.getTravelTitle()%><br>
여행지: <%=plan.getDestnation()%><br>
여행인원: <%=plan.getPerson()%>명<br>
여행 기간: <%=plan.getStartDate()%> ~ <%=plan.getEndDate()%><br>
예상 경비: <%=plan.getTravelMoney()%><br> 
<a href='update?no=<%=plan.getNo()%>'>변경</a>  
<a href='delete?no=<%=plan.getNo()%>'>삭제</a>  
<a href='../photoboard/list?planNo=<%=plan.getNo()%>'>여행사진첩</a>

<%
}
%>

<jsp:include page="../footer.jsp"></jsp:include>  