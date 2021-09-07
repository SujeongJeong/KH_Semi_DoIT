<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 - Do IT</title>
<!-- 외부 스타일 시트 -->
<link href='<%=request.getContextPath()%>/resources/css/all.css'
	rel='stylesheet'>
<style>
	#content{
	margin : 50px auto;
	height : auto;
	min-height : 100%;
	width: 800px;
	}

	.search{
		display: inline-block;
	}
	.createStudy{
		margin-left : 150px;
		margin-right : 20px;
	}
	.studyCategory{
		margin-left : 10px;
		margin-right : 20px;
	}
	.studyList{
		display:grid;
		grid-template-columns: repeat(5, 1fr);
		grid-auto-rows: minmax(200px, auto);
	}
	.studyImage{
		width:150px;
		height:150px;
	}
	.plusBtn{
		display:inline-block;
		margin-left:50%;
	}
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp 생성 -->
	<%@ include file='/WEB-INF/views/common/menubar.jsp'%>

	<div class="content" id="content">
		<h1>오픈 스터디</h1>
		<div class="search search1">
			<form>
				<input type="text" size="15px" name="search"
					placeholder="검색할 내용을 입력하세요"> <input type="submit"
					value="검색하기">
			</form>
		</div>
		<div class="search createStudy">
			<button>스터디 만들기</button>
		</div>
		<div class="search category">
			카테고리<button class="studyCategory" name="studyCategory">▼</button>
		</div>
		<div class="search canJoinStudy">
			<input type="checkbox" name="canJoinStudy">바로 참여 가능한 방
		</div>
		<br><br>
		<div class="studyList">
			<div class="studyRoom"><img class="studyImage" src='/resources/images/user.png'>스터디방 1</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 2</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 3</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 4</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 5</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 6</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 7</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 8</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 9</div>
			<div class="studyRoom"><img class="studyImage" src="/resources/images/user.png">스터디방 10</div>
		</div>
		<br><br>
		<div class="studyRoomPlus">
		<hr>
		<button class="plusBtn">더보기</button> 
		</div>
		
		
		
		
		
	</div>








	<footer>
		<%@ include file='/WEB-INF/views/common/footer.jsp'%>
	</footer>

</body>
</html>