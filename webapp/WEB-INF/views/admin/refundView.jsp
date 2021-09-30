<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/admin-Refund.css' rel='stylesheet'>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<%
	if(request.getAttribute("result") != null) {
		if(request.getAttribute("result").equals("success")) {
%>
<script>
	alert('환불 처리를 완료하였습니다.');
</script>
<%
	} else {
%>
<script>
	alert('환불 처리를 실패하였습니다.');
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
					<li class="current">
						<a href="<%= request.getContextPath() %>/admin/refund">환불내역</a>
					</li>
					<li class="report">
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
					<h1>환불 내역</h1>
					<div class="board-header">
						<div class="btn">
							<button type="button" onclick="return validate()">환불</button>
						</div>
					</div>
					<form name="refundForm" method="post">
						<table class="board-list">
							<thead>
								<tr>
	                                <th>신청번호</th>
	                                <th>회원번호</th>
									<th>이메일</th>
									<th>환불 신청 코인</th>
									<th>계좌번호</th>
									<th>은행</th>
									<th>예금주명</th>
	                                <th>신청일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="rl" items="${ refundList }">
									<tr>
										<td><input type="checkbox" class="cbx_chk" name="refundNo" value="${ rl.refundNo }">${ rl.refundNo }</td>
										<td>${ rl.userNo }</td>
										<td>${ rl.userEmail }</td>
										<td>${ rl.refundCoin }</td>
										<td>${ rl.bankAccount }</td>
										<td>${ rl.bankName }</td>
										<td>${ rl.accountName }</td>
										<td><fmt:formatDate value="${ rl.refundDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
					<ul class="board_paging">
					<li><a href="${ contextPath }/admin/refund?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
						<a href="${ contextPath }/admin/refund?page=${ pi.page - 1}${ searchParam }">&lt;</a>
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
								<a href="${ contextPath }/admin/refund?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
						<a href="${ contextPath }/admin/refund?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/admin/refund?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>
				</div>
			</content>
		</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<script>
		function validate(){
			var checked = $("input[name=refundNo]:checked").length;
			if(checked == 0) {
				alert('환불 처리를 할 체크박스를 선택해주세요');
				return false;
			} else if(confirm("환불처리를 하시겠습니까?")) {
				document.forms.refundForm.action = '${contextPath}/admin/refund';
				document.forms.refundForm.submit();
			}
			
		};
	</script>
</body>
</html>