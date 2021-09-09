<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랭킹 - Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div class="outer">
			<div class="group">
			<span class="title">범위 : </span>
			<input type="radio" name="all" value="all" checked><label for="all">전체</label>
			<input type="radio" name="study" value="study"><label for="study">스터디방</label>
			</div>
			<div class="period">
			<span class="title">기간 : </span>
			<input type="radio" name="yesterday" value="yesterday" checked><label for="yesterday">어제</label>
			<input type="radio" name="week" value="week"><label for="week">이번주</label>
			<input type="radio" name="month" value="month"><label for="month">이번달</label>		
			</div>
			<div class="">
			<span>yyyy년 MM월 dd일(E요일) 오전0시 기준</span>
			</div>
			<div class="ranking-wrapper">
			<div class="ranking"><img src="resources/images/flag-first.png" alt="1위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag-second.png" alt="2위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag-third.png" alt="3위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="4위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="5위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="6위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="7위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="8위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="9위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="ranking"><img src="resources/images/flag.png" alt="10위"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>
			<div class="me"><img src="resources/images/flag-me.png" alt="me"><span class="nickname">nickname</span><span class='hours'>00:00:00</span></div>			
			</div>
		</div>
	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>