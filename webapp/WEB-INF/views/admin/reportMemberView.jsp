<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/admin-ReportMember.css?after' rel='stylesheet'>
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
					<li>
						<a href="<%= request.getContextPath() %>/admin/home">회원관리</a>
					</li>
					<li>
						<a href="<%= request.getContextPath() %>/admin/study">스터디관리</a>
					</li>
					<li>
						<a href="<%= request.getContextPath() %>/admin/refund">환불내역</a>
					</li>
					<li class="report current">
						<a href="<%= request.getContextPath() %>/admin/reportMember">신고내역</a>
						<ul class="report-ul">
							<li><a href="<%= request.getContextPath() %>/admin/reportMember">-&ensp;회원</a></li>
							<li><a href="<%= request.getContextPath() %>/admin/reportStudy">-&ensp;스터디</a></li>
						</ul>
					</li>
				</ul>
			</nav>


        <!-- 관리자 content -->    
			<content class="content">
				<div id="board-list-wrap">
					<h1>신고내역 - 회원</h1>
					<div class="board-header">
						<c:if test="${ !empty param.category && !empty param.range }">
							<c:set var="searchParam" 
							value="&category=${ param.category }&range=${ param.range }"/>
						</c:if>
						<form method="get" action="${ contextPath }/admin/reportMember">
							<div class="search">
								구분 : 
								<select name="category">
									<option value="board" <c:if test="${ param.category == 'board' }">selected</c:if>>게시글</option>
									<option value="reply" <c:if test="${ param.category == 'reply' }">selected</c:if>>댓글</option>
									<option value="study-member" <c:if test="${ param.category == 'study-member' }">selected</c:if>>스터디 회원</option>
								</select>&emsp;
								정렬 : 
								<select name="range">
									<option value="latest" <c:if test="${ param.range == 'latest' }">selected</c:if>>최신순</option>
									<option value="report-count" <c:if test="${ param.range == 'report-count' }">selected</c:if>>신고 횟수</option>
									<option value="memberReport-count" <c:if test="${ param.range == 'memberReport-count' }">selected</c:if>>회원 누적 신고</option>
								</select>&emsp;
								<button type = "submit"><img src="../resources/images/search_btn.png"></button>
							</div>
						</form>
					</div>
					<table class="board-list">
						<caption>게시판 목록</caption>
						<thead>
							<tr>
                                <th>번호</th>
								<th>날짜</th>
								<th>구분</th>
								<th>신고당한 ID</th>
								<th>신고 횟수</th>
								<th>회원 누적신고</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach  var="r" items="${ reportMemberList }">
							<tr>
								<td>${ r.report_no }</td>
								<td>${ r.report_date }</td>
								<td class="reportPopup" onclick="boardDetailView(${r.board_no})">${r.subject}</td>
								<td>${ r.rnickname }</td>
								<td class="reportPopup" onclick="openPopup('<%= request.getContextPath() %>/reportList?br_no='+ ${r.br_no} + '&type=${r.subject}', 'reportList', 800, 500);">${ r.rcount }</td>
								<td class="reportPopup" onclick="openPopup('<%= request.getContextPath() %>/reportAllList?ruser_no='+ ${r.ruser_no}, 'reportAllList', 800, 500);">${ r.allcount }</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<ul class="board_paging">
					<li><a href="${ contextPath }/admin/reportMember?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
						<a href="${ contextPath }/admin/reportMember?page=${ pi.page - 1}${ searchParam }">&lt;</a>
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
								<a href="${ contextPath }/admin/reportMember?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
						<a href="${ contextPath }/admin/reportMember?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/admin/reportMember?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>
				</div>
			</content>



		</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<script>
	// 팝업창 호출
	function openPopup(url, title, width, height){
		// 왼쪽으로 부터 거리(가운데 맞추기)
		let left = (document.body.clientWidth/2) - (width/2);
		// 듀얼모니터를 위한 계산 (듀얼모니터를 쓰고 있을때 추가로 )
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		
		
		// 새창 열기
		window.open(url, title, options);
	}
	
	
	function boardDetailView(board_no){
		location.href = '<%= request.getContextPath() %>/qna/detail?board_no=' + board_no;
	}
	
	</script>
</body>
</html>