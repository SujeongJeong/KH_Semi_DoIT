<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 정보</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	.content {
		display: flex;
	 }
	 
	/* 사이드메뉴 영역 */
	.side_menu { 
		border: 1px solid black;
		width: 160px;
	}
	
	.side_menu ul {
		position: sticky;
	}
	.side_menu li {
		list-style: none;
	}
	.side_menu a {
		text-decoration:none;
		display : block;
		margin : 10px;
		color : #C4C4C4;
		width : 100px;
		text-align : left;
		font-size : 15pt;
	}
	.side_menu a:hover {
		color: #5FC5FF;
	}
	.side_menu .current a{
		color : #5FC5FF;
		font-weight : bold;
	}
	/* 메인 콘텐츠 영역 */
	.content_area {
		border: 1px solid black;
		width: 950px;
	}
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<content class="content">
		
		<nav class="side_menu">
			<li class="current"><a href="#">내 정보</a></li>
			<li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
			<li><a href="<%= request.getContextPath() %>/my/q&a">내 Q&A</a></li>
			<li><a href="<%= request.getContextPath() %>/my/details">결제 내역</a></li>
		</nav>
		<div class="content_area">
		
		</div>
	</content>
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>