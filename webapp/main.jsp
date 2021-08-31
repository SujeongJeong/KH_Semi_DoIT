<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%= request.getContextPath() %>/resources/css/all.css' rel='stylesheet'>
<style>
	.content{
		display : grid;
		grid-template-columns : repeat(2, 1fr);
		grid-gap :10px;
		border : 1px black solid;
		text-align : center;
		
	}
	.outer{
		width : 80%;
	}
	.study-area{
		grid-column : 1/2;
	}
	.goal-area{
		grid-column : 2/3;
	}
	.ranking-area{
		grid-column : 1/2;
	}
	.todo-area{
		grid-column : 2/3;
	}
	
	ul{
		list-style: none;
	}
	
	.title{
		font-weight : bold;
		font-size : 20px;
	}

</style>

</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp' %>
	<div class="content">
		<div class="outer">
			<div class="study-area">
				<label class="title">내 스터디</label><img src="resources/images/left-arrow-nolist.png" alt="넘김"><img src="resources/images/right-arrow-nolist.png" alt="넘김">
				<div class="study-list">
				<label class="nolist text">스터디를 만들거나 추가해보세요.</label>
				</div>
			</div>
			<div class="goal-area">
				<label class="title">오늘의 목표</label>
				<div class="goal-list">
					<ul>
						<li class="text">오늘 공부시간/목표 시간</li>
						<li><label class="todayhours">0시간 00분</label><label class="goalhours">/ 0시간 00분</label></li>
					</ul>
				</div>
			</div>
			<div class="ranking-area">
				<label class="title">누적 공부 시간 랭킹 </label><label class="point-c" class="yesterday">하루 전</label><label class="lightgray-c" class="date">yyyy.MM.dd(E요일) 오전 0시 기준</label>
				<ul>
					<li class="first"><img src="resources/images/flag-first.png" alt="1위">user01 22:59:59</li>
					<li class="second"><img src="resources/images/flag-second.png" alt="2위">user02 18:33:33</li>
					<li class="third"><img src="resources/images/flag-third.png" alt="3위">user03 10:00:00</li>
					<li class="myranking"><img src="resources/images/flag.png" alt="내랭킹">nickname 5:03:00</li>
				</ul>
			</div>
			<div class="todo-area">
				<label class="title">오늘의 할일</label><img src="resources/images/plus.png" alt="추가">
				<div class="todo-list">
					<label class="text">오늘의 할일을 추가하세요.</label>
				</div>
			</div>
		</div>
	</div>

	<footer>
	<%@ include file='/WEB-INF/views/common/footer.jsp' %>
	</footer>
</body>
</html>
