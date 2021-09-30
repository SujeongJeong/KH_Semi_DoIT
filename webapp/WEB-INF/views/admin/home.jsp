<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/admin-Member.css?aftters' rel='stylesheet'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<%
	if(request.getAttribute("result") != null) {
		if(request.getAttribute("result").equals("success")) {
%>
<script>
	alert('완료되었습니다');
</script>
<%
	} else {
%>
<script>
	alert('실패하였습니다.');
</script>
<%
		}
	}
%>	
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
			<!-- 관리자 nav -->
		<div class="admin-wrap">
			<nav id="admin-nav">
				<ul>
					<li class="current">
						<a href="<%= request.getContextPath() %>/admin/home">회원관리</a>
					</li>
					<li>
						<a href="<%= request.getContextPath() %>/admin/study">스터디관리</a>
					</li>
					<li>
						<a href="<%= request.getContextPath() %>/admin/refund">환불내역</a>
					</li>
					<li class="report">
						<a href="<%= request.getContextPath() %>/admin/reportMember">신고내역</a>
						<ul class="report-ul">
							<li><a href="<%= request.getContextPath() %>/admin/reportMember">-&ensp;회원</a></li>
							<%-- <li><a href="<%= request.getContextPath() %>/admin/reportStudy">-&ensp;스터디</a></li> --%>
						</ul>
					</li>
				</ul>
			</nav>

			<!-- 관리자 content -->   
			<content class="content">
				<div id="board-list-wrap">
					<h1>회 원</h1>
					<div class="board-header">
						<div class="btn">
							<button type="button" onclick="return memberDelete()">삭제</button>
							<button type="button" onclick="return adminGrant()">권한</button>
							<button type="button" onclick="return adminDisqualify()">박탈</button>
						</div>
					<form name ="memberListForm" method="get" action="<%= request.getContextPath() %>/admin/home">
						<div class="search">
							<button><img src="../resources/images/search_btn.png"></button><input type="text" placeholder="닉네임을 입력해주세요." name="searchValue" value="${ param.searchValue }">
						</div>
					</form>
					</div>
					<form name="memberForm" method="post">
						<table class="board-list">
							<caption>게시판 목록</caption>
							<thead>
								<tr>
									<th>번호</th>
									<th>이메일</th>
									<th>닉네임</th>
									<th>가입날짜</th>
									<th>신고횟수</th>
									<th>타입</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ml" items="${ memberList }">
								<tr>
									<td><input type="checkbox" class="cbx_chk" name="memberNo" value="${ ml.userNo }">${ ml.userNo }</td>
									<td>${ ml.userEmail }</td>
									<td>${ ml.nickName }</td>
									<td>${ ml.enrollDate }</td>
									<td>${ ml.reportCount }</td>
									<td>${ ml.userType }</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
					<ul class="board_paging">
					<li><a href="${ contextPath }/admin/home?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
						<a href="${ contextPath }/admin/home?page=${ pi.page - 1}${ searchParam }">&lt;</a>
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
								<a href="${ contextPath }/admin/home?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
						<a href="${ contextPath }/admin/home?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/admin/home?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>
				</div>
			</content>
		</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<script>
			
		// 회원 삭제
		function memberDelete(){
		var checked = $("input[name=memberNo]:checked").length;
			if(checked == 0) {
				alert('삭제 처리 할 회원을 선택해주세요');
				return false;
			} else if(confirm("회원을 삭제 하시겠습니까?")) {
				document.forms.memberForm.action = '${contextPath}/admin/memberDelete';
				document.forms.memberForm.submit();
			}
		}
		
		// 관리자 권한 부여
		function adminGrant(){
			var checked = $("input[name=memberNo]:checked").length;
			if(checked == 0) {
				alert('관리자 권환을 부여할 회원을 선택해주세요');
				return false;
			} else if(confirm("관리자 권환을 부여하시겠습니까?")) {
				document.forms.memberForm.action = '${contextPath}/admin/grant';
				document.forms.memberForm.submit();
			}
		}
		
		// 관리자 권한 박탈
		function adminDisqualify(){
			var checked = $("input[name=memberNo]:checked").length;
			if(checked == 0) {
				alert('관리자 권한을 박탈할 회원을 선택해주세요');
				return false;
			} else if(confirm("관리자 권한을 박탈하시겠습니까?")) {
				document.forms.memberForm.action = '${contextPath}/admin/disqualify';
				document.forms.memberForm.submit();
			}
		}
	</script>
</body>
</html>