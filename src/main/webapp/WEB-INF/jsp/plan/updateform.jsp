<%@page import="sinyj0622.mytrip.domain.Plan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="../header.jsp"></jsp:include>
<%
Plan plan = (Plan) request.getAttribute("update");
if (plan == null){
%>    
<p>해당 게시글을 찾을 수 없습니다</p>

<%
} else {
%>

<h1>여행 플랜 작성</h1>
<form action='update' method='post'>
번호: <input name='no' readonly type='text' value='<%=plan.getNo()%>'><br>
여행 주제: <input name='title'  type='text' value=<%=plan.getTravelTitle()%>><br>
여행지: <input name='place'  type='text' value=<%=plan.getDestnation()%>><br>
여행인원: <input name='person'  type='number' value=<%=plan.getPerson()%>>명<br>
시작일: <input name='sdt'  type='date' value=<%=plan.getStartDate()%>><br>
종료일: <input name='edt'  type='date' value=<%=plan.getEndDate()%>><br>
예상 경비: <input name='money' type='number' value=<%=plan.getTravelMoney()%>>만원<br>
<button>수정</button>

<%
}
%>

<jsp:include page="../footer.jsp"></jsp:include>