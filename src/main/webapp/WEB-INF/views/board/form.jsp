<%@page import="sinyj0622.mytrip.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<h1>게시물 입력(JSP)</h1>
<form action='add' method='post'>
내용:<br>
<textarea id="summernote" name='text' rows='5' cols='60'></textarea><br>
<button>등록</button>
</form>


<script type="text/javascript">
$(document).ready(function() {
	  //여기 아래 부분
	  $('#summernote').summernote({
	      height: 300,                 // 에디터 높이
	      minHeight: null,             // 최소 높이
	      maxHeight: null,             // 최대 높이
	      focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
	      lang: "ko-KR",          // 한글 설정
	      placeholder: '최대 2048자까지 쓸 수 있습니다'  //placeholder 설정
	          
	  });
	});
</script>
