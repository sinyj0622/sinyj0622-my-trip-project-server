<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>

<title>여행gogo</title>

 <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>
  <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/css/summernote/summernote-lite.css" rel="stylesheet">
<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
body {
  background-color: light;
}
div.container {
  background: white;
  width: 600px;
}
</style>
</head>
<body>
<tiles:insertAttribute name="header"/>
<hr style='margin: 15px;'>

<div class='container'>
<tiles:insertAttribute name="body"/>
</div>
<tiles:insertAttribute name="footer"/>
<script src="${pageContext.servletContext.contextPath}/js/summernote/summernote-lite.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/summernote/lang/summernote-ko-KR.js"></script>
<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>
<script src='${pageContext.getServletContext().getContextPath()}/js/auth/<tiles:getAsString name="jsFilename"></tiles:getAsString>?ver=1'></script>
</body>
</html>
