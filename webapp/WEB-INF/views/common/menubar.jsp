<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	// session 객체에 담긴 loginUser 정보를 변수에 담아두기
	Member loginUser = (Member)session.getAttribute("loginUser");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menubar</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/menubar-style.css' rel='stylesheet'>
<!-- 구글웹폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap" rel="stylesheet">
<%-- 세션에 담긴 msg 있을 경우 alert 처리하는 script 작성 --%>
	<% if(session.getAttribute("msg") != null){ %>
		<script>
			alert('<%= session.getAttribute("msg")%>');
		</script>
	<% 
		session.removeAttribute("msg");
		} %>

</head>
<body>
<div class="wrapper">
	<header id='header'>
		<div class="btnArea">
			<%-- 로그인이 된 상태와 로그인이 되지 않은 상태를 구분하기 위해 if문으로 조건식 추가 --%>
<%-- 			<% if(loginUser == null){ %> --%>
			<!-- 회원가입/로그인 -->
			<a class="login" href="<%= request.getContextPath() %>/login">로그인</a>
			<a class="join-or-out" href="<%= request.getContextPath() %>/memberJoin">회원가입</a>
<%-- 			<% } else { %> --%>
<%-- 					
			<a href="<%= request.getContextPath() %>/my/home"><img class="user" src="<%= request.getContextPath() %>/resources/images/user.jpg" alt="user"</a>
			<a href="<%= request.getContextPath() %>/my/home"><img class="user" src="<%= request.getContextPath() %>/resources/images/admin.jpg" alt="admin"</a>
			<a class="join-or-out" href="<%= request.getContextPath() %>/logout">로그아웃</a> --%>
<%-- 			<% } %> --%>
		</div>
		<!-- 로고 이미지를 클릭하면 첫 화면으로 -->
		<div class="logo-area">
		<a href='<%= request.getContextPath() %>'>
			<img class="Logo" src="<%= request.getContextPath() %>/resources/images/logo.png" alt="logo">
		</a>
		</div>
	</header>
	<nav id="nav">
		<ul >
			<li><a href="<%= request.getContextPath() %>">홈</a></li>
			<li><a href="<%= request.getContextPath() %>/ranking">랭킹</a></li>
			<li><a href="<%= request.getContextPath() %>/study/home">스터디</a></li>
			<li><a href="<%= request.getContextPath() %>/shop/home">Shop</a></li>
			<li><a href="<%= request.getContextPath() %>/qna/home">Q&A</a></li>
			<li><a href="<%= request.getContextPath() %>/my/home">마이페이지</a></li>
			<%-- 관리자 로그인 경우 관리페이지 --%>
			<li><a href="<%= request.getContextPath() %>/admin/home">관리페이지</a></li>
			
		</ul>
	</nav>	
	<hr>
</div>
</body>
</html>