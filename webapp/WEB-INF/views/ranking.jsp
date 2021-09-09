<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랭킹 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css?after' rel='stylesheet'>
<style>
.title{ font-weight : bold;  font-size : 20px;  }
ul{	list-style: none;	}
li{ font-size : 18px; padding: 10px; }
li span{ margin-right : 10px; margin-left : 10px; }
.ranking { border-bottom: 1px solid #E5E5E5; }
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div class="outer">
			<div class="group-wrapper">
			<span class="title">범위 : </span>
			<input type="radio" name="group" value="all" checked><label for="all">전체</label>
			<input type="radio" name="group" value="study"><label for="study">스터디방</label>
			</div>
			<div class="period-wrapper">
			<span class="title">기간 : </span>
			<input type="radio" name="period" value="yesterday" checked><label for="yesterday">어제</label>
			<input type="radio" name="period" value="week"><label for="week">이번주</label>
			<input type="radio" name="period" value="month"><label for="month">이번달</label>		
			</div>
			
			
			<div class="standard">
			<span>
			<c:if test="${ period eq 'yesterday' }">yyyy년 MM월 d일(E요일) 오전0시 기준</c:if>
			<c:if test="${ period eq 'week' }">yyyy년 MM월 d일(월요일) 오전0시 기준</c:if>
			<c:if test="${ period eq 'month' }">yyyy년 MM월 1일(E요일) 오전0시 기준</c:if>
			</span>
			</div>
			
			<ul class="ranking-wrapper">
			<li class="ranking"><img src="resources/images/flag-first.png" alt="1위"><span>1위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag-second.png" alt="2위"><span>2위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag-third.png" alt="3위"><span>3위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="4위"><span>4위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="5위"><span>5위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="6위"><span>6위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="7위"><span>7위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="8위"><span>8위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="9위"><span>9위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="ranking"><img src="resources/images/flag.png" alt="10위"><span>10위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>
			<li class="me"><img src="resources/images/flag-me.png" alt="me"><span>?위</span><span class="nickname">nickname</span><span class='hours'>00:00:00</span></li>			
			</ul>
		</div>
	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>