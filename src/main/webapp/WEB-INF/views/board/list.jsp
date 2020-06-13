<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="card" style="width: 18rem;">
  <img src="..." class="card-img-top" alt="...">
  <div class="card-body">
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div>

  <a href='add'>새 글</a><br>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
<jsp:useBean id="list" 
  type="java.util.List<Board>"
  class="java.util.ArrayList"
  scope="request"/>
<% 
  for(Board item : list) {
    pageContext.setAttribute("item", item);
%>
  <tr>
    <td>${item.no}</td> 
    <td><a href='detail?no=${item.no}'>=> ${item.text}</a></td> 
    <td>${item.date}</td> 
    <td>${item.viewCount}</td>
  </tr>
<%
  }
%>
</table>

