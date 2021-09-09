<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 Q&A</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	/* 전체를 감싸는 div */
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
	div[class$=list] {
		border: 1px solid black;
		width: 100%;
		height: 330px;
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
	}
	div[class^=my] p {
		color: lightgray;
		margin-top: 20%;
		text-align: center;
	}
	
</style>
</head>
<body>
<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<content class="content">
		
		<nav class="side_menu">
			<li><a href="<%= request.getContextPath() %>/my/home">내 정보</a></li>
			<li><a href="<%= request.getContextPath() %>/my/study">내 스터디</a></li>
			<li class="current"><a href="#">내 Q&A</a></li>
			<li><a href="<%= request.getContextPath() %>/my/details">결제 내역</a></li>
		</nav>
		<div class="content_area">
			<div class="my_board">
				<h2>내가 작성한 게시글</h2>
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
				<h2>내가 댓글을 작성한 게시글</h2>
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
	</content>
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>