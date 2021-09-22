<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// session 갹체에 담긴 loginUSer 정보를 변수에 담아두기
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<% 	String nav1 = (String)request.getAttribute("nav1"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menubar</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/menubar-style.css?after' rel='stylesheet'>
<!-- 구글웹폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap" rel="stylesheet">
<%-- 세션에 담긴 msg 있을 경우 alert 처리하는 script 작성 --%>
<% if(session.getAttribute("msg") != null) { %>
<script>
	 alert('<%= session.getAttribute("msg") %>');
</script>
<% 
		session.removeAttribute("msg");	
	} 
%>


</head>
<body>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="application"/>
<div class="wrapper">
	<header id='header'>
		<div class="btnArea">
			<c:choose>
				<c:when test="${ loginUser == null }">
				<!-- 회원가입/로그인 -->
				<a class="login" href="${ contextPath }/login">로그인</a>
				<a class="join-or-out" href="${ contextPath }/memberJoin">회원가입</a>
				</c:when>
				<c:otherwise>
					<c:if test="${ loginUser.userType =='U'  }">
					<a href="${ contextPath }/my/home"><img class="user" src="${ contextPath }${ loginUser.profileImg }" alt="user"></a>
					</c:if>
					<c:if  test="${ loginUser.userType =='A'  }">
					<a href="${ contextPath }/my/home"><img class="user" src="${ contextPath }/resources/images/admin.png" alt="admin"></a>
					</c:if>
				<span>${ loginUser.nickName }</span>
				<a class="join-or-out" href="${ contextPath }/logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- 로고 이미지를 클릭하면 첫 화면으로 -->
		<div class="logo-area">
		<a href='${ contextPath }'>
			<img class="Logo" src="${ contextPath }/resources/images/logo.png" alt="logo">
		</a>
		</div>
	</header>
	<nav id="nav">
		<ul class="navList">
			<li><a href="${ contextPath }"<% if (nav1 == null) { %>class="current"<%}%>>홈</a></li>
			<li><a href="${ contextPath }/ranking"<% if ("ranking".equals(nav1)) { %>class="current"<%}%>>랭킹</a></li>
			<li><a href="${ contextPath }/study/home"<% if ("study".equals(nav1)) { %>class="current"<%}%>>스터디</a></li>
			<li><a href="${ contextPath }/shop/home"<% if ("shop".equals(nav1)) { %>class="current"<%}%>>Shop</a></li>
			<li><a href="${ contextPath }/qna/home"<% if ("qna".equals(nav1)) { %>class="current"<%}%>>Q&amp;A</a></li>
			<c:choose>
			<c:when test="${ loginUser == null || loginUser.userType == 'U' }">
			<li class="mypage"><a href="${ contextPath }/my/home"<% if ("my".equals(nav1)) { %>class="current"<%}%>>마이페이지</a></li>
			</c:when>
			<c:when  test="${ loginUser != null && loginUser.userType =='A'  }">
			<li><a href="${ contextPath }/admin/home"<% if ("admin".equals(nav1)) { %>class="current"<%}%>>관리페이지</a></li>
			</c:when>
			</c:choose>
		</ul>
		</nav>	
	</div>

	<c:if test="${ loginUser == null }">
	<script>
		$(".mypage").click(function(){
			 if(confirm("로그인 하시겠습니까?")){
				 location.href="${ contextPath}/login";
			 }
		  });
	</script>
	</c:if>

	<script src="${ contextPath }/resources/js/imagePreview.js"></script>

</body>
</html>