<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 Q&A</title>
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
	 
	/* 메인 콘텐츠 영역 */
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
	div[class^=my] p {
		color: lightgray;
		margin-top: 15%;
		text-align: center;
	}
	/* 콘텐츠 영역 */
	
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<div class="my_wrap">
		<nav class="side_menu">
			<ul>
				<li><a href="<%= request.getContextPath() %>/my/home">내 정보</a></li>
				<li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
				<li class="current"><a href="#">내 Q&A</a></li>
				<li><a href="<%= request.getContextPath() %>/my/details">결제 내역</a></li>
			</ul>
		</nav>
		<div class="content">
			<div class="my_board">
				<h1>내가 작성한 게시글</h1>
				<div class="board_list">
					<ul class="list_header">
						<li>글 제목</li>
						<li>작성자</li>
						<li>작성일</li>
					</ul>
					<p>작성한 글이 없습니다</p>
				</div>
			</div>
			<div class="my_comment">
				<h1>내가 댓글을 작성한 게시글</h1>
				<div class="comment_list">
					<ul class="list_header">
						<li>글 제목</li>
						<li>작성자</li>
						<li>작성일</li>
					</ul>
					<p>작성한 댓글이 없습니다</p>
				</div>
			</div>
		</div>
	</div>
	
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>