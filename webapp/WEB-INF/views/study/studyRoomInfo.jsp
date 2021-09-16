<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디방 상세 – Do IT</title>
<style>
*{
	text-align:center;
}
#outer {
	width: 100%;
	height: 100%;
}

.Top {
	position: relative;
}

#studyName {
	position: absolute;
	width: 300px;
	height: 75px;
	top: 10px;
	left: 200px;
	font-size: 1.2em;
}

#nickName {
	position: absolute;
	width: 150px;
	height: 50px;
	top: 135px;
}

#studyTO {
	position: absolute;
	width: 135px;
	height: 100px;
	top: 115px;
	left: 525px;
}

#backImage {
	width: 100%;
	height: 200px;
	opacity: 0.5;
}

.mid1 {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	gap: 30px 10px;
}

.sDay, .sTime, .sPeriod {
	display: flex;
	justify-content: center;
}

.category {
	margin-top: 15px;
	color:#C4C4C4;
}

.studyExplain, .studyNotice {
	display: inline-block;
	width: 90%;
	height: 175px;
	border: 1px solid black;
	border-color : #E5E5E5; border-radius:5px;
	
}

.studyNotice {
	background-color: #E5E5E5;
}

.mid2, .mid3 {
	display: flex;
	justify-content: center;
}

#studyRoomBtn {
	width: 65%;
	height: 45px;
	background-color: #5FC5FF;
	font-size: 1.2rem;
	color: white;
	font-weight: bold;
	border-radius: 5px;
	border: 0;
	outline: 0;
}

#report {
	margin-left: 25px;
	width: 20%;
	height: 45px;
	color: red;
	font-weight: bold;
	backround-color:#E5E5E5;
	font-size: 1.2rem;
	border-radius: 5px;
	border: 0;
	outline: 0;
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
			<div id="studyName">
				<h2>스터디방 이름</h2>
			</div>
			<div id="nickName">
				<h3>방장 아이디</h3>
			</div>
			<div id="studyTO">
				<h3>
					스터디 정원<br>n/m명
				</h3>
			</div>
			


		</div>
		<div class="mid1">
			<div>
				<h3>공부 요일</h3>
				<div class="sDay">설정한 날짜 표시</div>
			</div>

			<div>
				<h3>스터디 기간</h3>
				<div class="sPeriod">시작 날짜 ~ 종료 날짜</div>
			</div>

			<div>
				<h3>공부 시간</h3>
				<div class="sTime">시작 시간 ~ 종료 시간</div>
			</div>

			<div class="category">
				<h3 class="sCategory">#설정한 카테고리</h3>
			</div>
		</div>
		<hr>
		<h2>소개글</h2>
		<div class="mid2">
			<div class="studyExplain">
				<h3>
					스터디방 생성시 입력한 소개(설명)<br> 영역 확인용 테두리, 추후 테두리 삭제 예정<br> 해당
					영역 이상의 내용 있을 시 스크롤바 생성
				</h3>
			</div>
		</div>
		<h2>공지사항</h2>
		<div class="mid3">
			<div class="studyNotice">
				<h3>
					스터디방 생성시 입력한 공지사항(주의사항,규칙) 출력<br> 해당 영역 이상의 내용 있을 시 스크롤바 생성
				</h3>
				</h3>
			</div>
		</div>
		<br>
		<div class="bottom">
			<button id="studyRoomBtn"
				onclick="opener.location.href='<%=request.getContextPath()%>/study/enterStudy';window.close();">로그인
				/ 가입하기 / 입장하기</button>
			<button id="report">신고하기</button>
		</div>

	</div>

</body>
</html>











