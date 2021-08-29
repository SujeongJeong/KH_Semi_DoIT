<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Do IT</title>
<!-- 외부 스타일 시트 -->
	<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>

</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	
	<div class="content">
		<div class="study area">
			<label class="title">내 스터디</label><img src="resources/images/left-arrow-nolist.png" alt="넘김"><img src="resources/images/right-arrow-nolist.png" alt="넘김">
			<div class="study list">
			<label class="nolist text">스터디를 만들거나 추가해보세요.</label>
			</div>
		</div>
		<div class="goal area">
			<label class="title">오늘의 목표</label>
			<div class="goal list">
				<label class="text">오늘 공부시간/목표 시간</label>
				<label class="todayhours">0시간 00분</label><label class="goalhours">/ 0시간 00분</label>
			</div>
		</div>
		<div class="ranking area">
			<label class="title">누적 공부 시간 랭킹</label><label class="yesterday">하루 전</label><label class="date">yyyy.MM.dd(E요일) 오전 0시 기준</label>
			<img src="resources/images/flag-first.png" alt="1위"><label>user01</label><label>22:59:59</label>
			<img src="resources/images/flag-second.png" alt="2위"><label>user02</label><label>18:33:33</label>
			<img src="resources/images/flag-third.png" alt="3위"><label>user03</label><label>10:00:00</label>
			<div class="myranking">
			<img src="resources/images/flag.png" alt="내랭킹"><label>nickname</label><label>5:03:00</label>
			</div>
		</div>
		<div class="todoarea">
			<label class="title">오늘의 할일</label><img src="resources/images/plus.png" alt="추가">
			<div class="todo list">
				<label class="text">오늘의 할일을 추가하세요.</label>
			</div>
		</div>
	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>
