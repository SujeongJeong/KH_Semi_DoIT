<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방</title>
<style>
#outer {
	width: 100%;
	height: 100%;
}

.Top{
	position:relative;
}
#studyName{
	position:absolute;
	width:300px;
	height:75px;
	top:10px;
	left:200px;
	font-size:1.2em;
}
#nickName{
	position:absolute;
	width:150px;
	height:50px;
	top:135px;
}

#studyTO{
	position:absolute;
	width:135px;
	height:100px;
	top:115px;
	left:525px;
}

#report{
	position:absolute;
	width:100px;
	height:30px;
	top:10px;
	left:550px;
	background-color:red;
	color:black;
	font-weight:bold;
}

#backImage {
	width: 100%;
	height: 200px;
	opacity:0.5;
}

.mid1 {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
}

.category {
	margin-top: 25px;
}

.studyExplain,
.studyNotice {
	display: inline-block;
	width: 90%;
	height: 175px;
	border: 1px solid black;
}

.studyNotice{
	background-color:#E5E5E5;
}

.mid2,
.mid3{
	display:flex;
	justify-content:center;
}

#studyRoomBtn{
	width:100%;
	height:45px;
	background-color:#5FC5FF;
	font-size:1.35em;
	color:white;
	font-weight:bold;
}

h2, h3 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="outer">
		<div class="Top">
			<img id="backImage"
				src="/Do_IT/resources/images/study-background1.jpg">
			<div id="studyName"><h2>스터디방 이름</h2></div>
			<div id="nickName"><h3>방장 아이디</h3></div>
			<div id="studyTO"><h3>스터디 정원<br>n/m명</h3></div>
			<button id="report">신고하기</button>
				
				
		</div>
		<div class="mid1">
			<div>
				<h2>공부 요일</h2>
				<h3>설정한 날짜 표시</h3>
			</div>

			<div>
				<h2>스터디 기간</h2>
				<h3>시작 날짜 ~ 종료 날짜</h3>
			</div>

			<div>
				<h2>공부 시간</h2>
				<h3>시작 시간 ~ 종료 시간</h3>
			</div>

			<div class="category">
				<h3>#설정한 카테고리</h3>
			</div>
		</div>
		<hr>
			<h2>소개글</h2>
		<div class="mid2">
			<div class="studyExplain">
				<h3>스터디방 생성시 입력한 소개(설명)<br>
				영역 확인용 테두리, 추후 테두리 삭제 예정<br>
				해당 영역 이상의 내용 있을 시 스크롤바 생성 </h3>
			</div>
		</div>
		<br>
		<div class="mid3">
			<div class="studyNotice">
			<h2>공지사항</h2>
			<h3>스터디방 생성시 입력한 공지사항(주의사항,규칙) 출력<br>
			해당 영역 이상의 내용 있을 시 스크롤바 생성 </h3></h3>
			</div>
		</div>
		<br>
		<div class="bottom">
			<button id="studyRoomBtn" onclick="opener.location.href='<%=request.getContextPath()%>/enterStudy';window.close();">로그인 / 가입하기 / 입장하기</button>
		</div>

	</div>

</body>
</html>











