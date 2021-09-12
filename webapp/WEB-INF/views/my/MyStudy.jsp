<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 스터디</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	/* 전체 감싸는 div */
	.my_wrap {
		display: flex;
	}
	/* 사이드메뉴 영역 */
	.side_menu{
	    width: 160px;
	}
	.side_menu ul {
		padding-left: 40px;
		list-style-type:none;
	}
	.side_menu li {
		 margin: 60px 0;
	}
	.side_menu a{
		text-decoration:none;
		display : block;
		margin : 10px;
		color : #C4C4C4;
		width : 100px;
		text-align : left;
		font-size : 15pt;
	}
	.side_menu a:hover:not(.current){
		color : #5FC5FF;
	}
	
	.side_menu .current a{
		color : #5FC5FF;
		font-weight : bold;
	}
	
	/* 콘텐츠 영역 */
	div[class$=list] {
		border: 1px solid black;
		width: 100%;
		height: 350px;
	}
	.list_header {
		background: #5FC5FF;
		color: white;
		font-weight: bold;
		margin: 0;
		padding: 0;
		display: flex;
		justify-content: space-around;
		list-style: none;
		height: 50px;
	}
	.list_header li {
		font-size: 18px;
		padding: 10px 0;
	}
	div[class$=study] p {
		color: lightgray;
		margin-top: 15%;
		text-align: center;
	}
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<div class="my_wrap">
		<nav class="side_menu">
			<ul>
				<li><a href="<%= request.getContextPath() %>/my/home">내 정보</a></li>
				<li class="current"><a href="#">내 스터디</a></li>
				<li><a href="<%= request.getContextPath() %>/my/q&a">내 Q&A</a></li>
				<li><a href="<%= request.getContextPath() %>/my/details">결제 내역</a></li>
			</ul>
		</nav>
	<div class="content">
			<div class="open_study">
				<h1>내가 개설한 스터디</h1>
				<div class="open_list">
					<ul class="list_header">
						<li>스터디명</li>
						<li>공부 시간</li>
						<li>현재 인원</li>
						<li>개설일</li>
					</ul>
					<p>개설한 스터디가 없습니다</p>
				</div>
			</div>
			<div class="join_study">
				<h1>내가 참여중인 스터디</h1>
				<div class="join_list">
					<ul class="list_header">
						<li>스터디명</li>
						<li>공부 시간</li>
						<li>현재 인원</li>
						<li>개설일</li>
					</ul>
					<p>참여중인 스터디가 없습니다</p>
				</div>
			</div>
		</div>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>