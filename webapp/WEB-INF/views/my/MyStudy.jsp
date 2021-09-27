<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 스터디</title>
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?after' rel='stylesheet'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<style>
	/* 전체 감싸는 div */
	.my_wrap {
		display: flex;
	}
	/* 사이드메뉴 영역 */
	.side_menu{
	    width: 160px;
	    padding-left : 5%;
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
				<h2>내가 개설한 스터디</h2>
				<table class="board_list">
	                <thead>
	                    <tr>
	                        <th>스터디 번호</th>
	                        <th>카테고리</th>
	                        <th>스터디명</th>
	                        <th>현재 인원수</th>
	                        <th>개설자</th>
	                        <th>개설일</th>
	                        <th>종료일</th>
	                        <th></th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:forEach var="mos" items="${ MyOpenStudyList }">
							<tr>
								<td>${ mos.s_no }</td>
								<td>${ mos.cname }</td>
								<td>${ mos.s_name }</td>
								<td>${ mos.s_to }</td>
								<td>${ mos.user_nkname }</td>
								<td>${ mos.s_startPeriod }</td>
								<td>${ mos.s_endPeriod }</td>
								<td>
									<form name="deleteOpenStudy" method="post">
										<input type="hidden" name="deleteSNo" value="${ mos.s_no }">
									</form>
									<button id="deleteStudy" onclick="checkDelete()">삭제하기</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
	            </table>
	            <ul class="board_paging">
					<li><a href="${ contextPath }/my/study?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
						<a href="${ contextPath }/my/study?page=${ pi.page - 1}${ searchParam }">&lt;</a>
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
						<a href="${ contextPath }/my/study?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/my/study?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>
			</div>
			<div class="join_study">
				<h2>내가 참여중인 스터디</h2>
				<table class="board_list">
	                <thead>
	                    <tr>
	                        <th>스터디 번호</th>
	                        <th>카테고리</th>
	                        <th>스터디명</th>
	                        <th>현재 인원수</th>
	                        <th>개설자</th>
	                        <th>개설일</th>
	                        <th>종료일</th>
	                        <th></th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:forEach var="mjs" items="${ MyJoinStudyList }">
							<tr>
								<td>${ mjs.s_no }</td>
								<td>${ mjs.cname }</td>
								<td>${ mjs.s_name }</td>
								<td>${ mjs.s_to }</td>
								<td>${ mjs.user_nkname }</td>
								<td>${ mjs.s_startPeriod }</td>
								<td>${ mjs.s_endPeriod }</td>
								<td>
									<form name="exitJoinStudy" method="post">
										<input type="hidden" name="exitSNo" value="${ mjs.s_no }">
									</form>
									<button type="button" onclick="checkExit()">나가기</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
	            </table>
			</div>
		</div>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
		function checkDelete(){
			if(confirm('스터디를 삭제하시면 복구가 불가능하며 기존의 사용자 모두 참여할 수 없습니다. 정말 삭제하시겠습니까?')){
				document.forms.deleteOpenStudy.action = '${contextPath}/my/study';
				document.forms.deleteOpenStudy.submit();
			} else {
				return false;
			}
			
		}
		
		function checkExit(){
			if(confirm('스터디를 나가시면 복구가 불가능합니다. 정말 나가시겠습니까?')){
				document.forms.exitJoinStudy.action = '${contextPath}/my/study';
				document.forms.exitJoinStudy.submit();
			} else {
				return false;
			}
			
		}
	</script>
</body>
</html>