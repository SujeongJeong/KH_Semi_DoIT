<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css?after' rel='stylesheet'>
<link href='<%= request.getContextPath() %>/resources/css/main.css?after' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<style>
.outer{
		width : 1000px;
		display : grid;
		margin : 20px auto;	
		grid-template-rows : 400px 400px;
		grid-template-columns: 500px 500px;
		gap : 30px 40px ;
		 
	}
	.outer:nth-child(1){ 	grid-area : 1/1/2/2;	}
	.outer:nth-child(2){	grid-area : 1/2/2/3;    }
	.outer:nth-child(3){	grid-area : 2/1/3/2;	}
	.outer:nth-child(4){	grid-area : 2/2/3/3;	}
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<div class="content">
		<div class="outer">
			<div class="study-area">
				<span class="title">내 스터디</span>
				<span class = "icon">
				<img src="resources/images/left-arrow-nolist.png" alt="넘김"><img src="resources/images/right-arrow-nolist.png" alt="넘김">
				</span>
				<div class="study-list">
					<%-- <label class="nolist text">스터디를 만들거나 추가해보세요.</label>--%>
					<div class="study-info">
					<img src="resources/images/study-background1.jpg" alt="스터디배경사진"><br>
					<label class="study-name">스터디방 이름</label><br>
					<label class="study-category darkgray-c">#category</label>
					</div>
					<div class="study-info">
					<img src="resources/images/study-background1.jpg" alt="스터디배경사진"><br>
					<label class="study-name">스터디방 이름</label><br>
					<label class="study-category darkgray-c">#category</label>
					</div>
				</div>
			</div>
			<div class="goal-area">
				<label class="title">오늘의 목표</label>
				<div class="goal-list">
					<ul class="goalUl">
						<li class="semiTitle">오늘 공부시간/목표 시간</li>
						<li><span class="todayhours point-c">0시간 00분</span><span class="goalhours lightgray-c">/ 0시간 00분</span></li>
					</ul>
				</div>
			</div>
			<div class="ranking-area">
				<span class="title">누적 공부 시간 랭킹 </span><span class="point-c yesterday">하루 전</span>
				<span class="lightgray-c date">yyyy.MM.dd(E요일) 오전 0시 기준</span>
				<ul class="rankikgUl">
				<li class="first"><img src="resources/images/flag-first.png" alt="1위"><span class="nickname">1위 user01</span><span class="hours">22:59:59</span></li>
				<li class="second"><img src="resources/images/flag-second.png" alt="2위"><span class="nickname">2위 user02</span><span class="hours">18:33:33</span></li>
				<li class="third"><img src="resources/images/flag-third.png" alt="3위"><span class="nickname">3위 user03</span><span class="hours">10:00:00</span></li>
				<li class="myranking"><img src="resources/images/flag-me.png" alt="내랭킹"><span class="nickname">?위 nickname</span><span class="hours">5:03:00</span></li>
				</ul>
			</div>
			<div class="todo-area">
				<div class="todo-title">
					<label class="title">오늘의 할일</label><img src="resources/images/plus.png" alt="추가">
				</div>
				<div class="todo-list">
					<div class="hiddenScroll">
					<div class="scrollBlind">
						<ul class="list">
							<li>두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
							<li>두잇두잇두잇<button class="edit"></button><button class="delete"></button></li>
						</ul>
						<%-- <label class="nolist text">오늘의 할일을 추가하세요.</label>--%>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
	<script>
		$(".edit").click(function(){
			$(this).parent().html().replaceWith("<textarea>"+parent().value()+"</textarea>");
		});
	</script>
</body>
</html>
