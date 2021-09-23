<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 Q&A</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?after' rel='stylesheet'>
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
				<table class="board_list">
	                <thead>
	                    <tr>
	                        <th>글 번호</th>
	                        <th>카테고리</th>
	                        <th>글 제목</th>
	                        <th>작성자</th>
	                        <th>작성일</th>
	                        <th>조회수</th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:forEach var="mb" items="${ MyboardList }">
							<tr>
								<td>${ mb.board_no }</td>
								<td>${ mb.cname }</td>
								<td class="tit" onclick="boardDetailView(${mb.board_no})">${ mb.board_title }</td>
								<td>${ mb.nickname }</td>
								<td>${ mb.create_date }</td>
								<td>${ mb.count }</td>
							</tr>
						</c:forEach>
					</tbody>
	            </table>
	            <ul class="board_paging">
					<li><a href="${ contextPath }/my/q&a?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
						<a href="${ contextPath }/my/q&a?page=${ pi.page - 1}${ searchParam }">&lt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&lt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 페이지 목록(최대 10개) -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
					<li>
						<c:choose>
							<c:when test="${ p eq pi.page }">
								<a href="#" class="current_page">${ p }</a>
							</c:when>
							<c:otherwise>
								<a href="${ contextPath }/my/q&a?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
						<a href="${ contextPath }/my/q&a?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/my/q&a?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>
			</div>
			<div class="my_comment">
				<h1>내가 댓글을 작성한 게시글</h1>
				<table class="board_list">
	                <thead>
	                    <tr>
	                        <th>글 번호</th>
	                        <th>카테고리</th>
	                        <th>글 제목</th>
	                        <th>작성자</th>
	                        <th>작성일</th>
	                        <th>조회수</th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:forEach var="mr" items="${ MyReplyList }">
							<tr>
								<td>${ mr.board_no }</td>
								<td>${ mr.cname }</td>
								<td class="tit" onclick="boardDetailView(${mr.board_no})">${ mr.board_title }</td>
								<td>${ mr.nickname }</td>
								<td>${ mr.create_date }</td>
								<td>${ mr.count }</td>
							</tr>
						</c:forEach>
					</tbody>
	            </table>
	            <ul class="board_paging">
					<li><a href="${ contextPath }/my/q&a?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi2.page > 1 }">
						<a href="${ contextPath }/my/q&a?page=${ pi2.page - 1}${ searchParam }">&lt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&lt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 페이지 목록(최대 10개) -->
					<c:forEach var="p" begin="${ pi2.startPage }" end="${ pi2.endPage }">
					<li>
						<c:choose>
							<c:when test="${ p eq pi2.page }">
								<a href="#" class="current_page">${ p }</a>
							</c:when>
							<c:otherwise>
								<a href="${ contextPath }/my/q&a?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi2.page < pi2.maxPage }">
						<a href="${ contextPath }/my/q&a?page=${ pi2.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/my/q&a?page=${ pi2.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function boardDetailView(board_no){
					location.href='<%= request.getContextPath() %>/qna/detail?board_no=' + board_no;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function boardDetailView(){
					alert('로그인 후 이용 가능합니다.');
					location.href='<%= request.getContextPath() %>/login';
				}
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>