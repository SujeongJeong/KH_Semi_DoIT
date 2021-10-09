<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
	<link href='<%= request.getContextPath() %>/resources/css/qna-main.css?afters' rel='stylesheet'>
</head>
<body>
	
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
          <!-- 광고배너 -->						
          <div id="banner"><a href="${ contextPath }/shop/home"><img id="bannerImg" src="/Do_IT/resources/images/banner_shop.png"></a></div>
        
	<div class="content">
		<div id="board_list_wrap">
         <div class="board-header">
       
            <form method="get" action="<%= request.getContextPath() %>/qna/home">
	            <div class="search">
	                <button type="submit"><img src="../resources/images/search_btn.png"></button><input type="text" placeholder="제목을 입력해주세요." name="searchValue" value="${ param.searchValue }">
	            </div>
            </form>
            <div class="newpost_btn">
                    <button type="button" onclick="newWriting()">새 글 쓰기</button>
            </div>
        </div>
            <table class="board_list">
                <caption>게시판 목록</caption>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>구분</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    
						<c:forEach var="n" items="${ noticeList }">
							<tr class = "manager"<%--  onclick="detailView(${ n.notice_no });" --%>>
								<td></td>
								<td></td>
								<td class="tit" onclick="noticeDetailView(${n.notice_no})">${ n.notice_title }</td>
								<td>관리자</td>
								<td>${ n.create_date }</td>
								<td>${ n.count }</td>
							</tr>
						</c:forEach>
                    
                    	<c:forEach var="b" items="${ boardList }">
							<tr>
								<td>${ b.board_no }</td>
								<td>${ b.cname }</td>
								<td class="tit" onclick="boardDetailView(${b.board_no})">${ b.board_title }</td>
								<td>${ b.nickname }</td>
								<td>${ b.create_date }</td>
								<td>${ b.count }</td>
							</tr>
						</c:forEach>

					
                </tbody>
            </table>
            
            <!-- 맨 처음으로(<<) -->
            	<ul class="board_paging">
					<li><a href="${ contextPath }/qna/home?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
						<a href="${ contextPath }/qna/home?page=${ pi.page - 1}${ searchParam }">&lt;</a>
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
								<a href="${ contextPath }/qna/home?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
						<a href="${ contextPath }/qna/home?page=${ pi.page + 1 }${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨  끝으로(>>) -->
					<li><a href="${ contextPath }/qna/home?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
				</ul>

        </div>

	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function newWriting(){
					location.href = '<%= request.getContextPath() %>/qna/insert';
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function newWriting(){
					alert('로그인 후 이용 가능합니다.');
					location.href= '<%= request.getContextPath() %>/login';
				}
			</script>
		</c:otherwise>
	</c:choose>
	
	
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function noticeDetailView(notice_no){
					location.href = '<%= request.getContextPath() %>/notice/detail?notice_no=' + notice_no;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function noticeDetailView(){
					alert('로그인 후 이용 가능합니다.');
					location.href= '<%= request.getContextPath() %>/login';
				}
			</script>
		</c:otherwise>
	</c:choose>
	
		<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function boardDetailView(board_no){
					location.href = '<%= request.getContextPath() %>/qna/detail?board_no=' + board_no;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function boardDetailView(){
					alert('로그인 후 이용 가능합니다.');
					location.href= '<%= request.getContextPath() %>/login';
				}
			</script>
		</c:otherwise>
	</c:choose>

</body>
</html>